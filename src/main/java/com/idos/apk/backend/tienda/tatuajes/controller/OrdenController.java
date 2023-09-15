package com.idos.apk.backend.tienda.tatuajes.controller;

import com.idos.apk.backend.tienda.tatuajes.dto.detalle.DetalleOrdenInDto;
import com.idos.apk.backend.tienda.tatuajes.dto.orden.*;
import com.idos.apk.backend.tienda.tatuajes.exceptions.OrdenNotFoundException;
import com.idos.apk.backend.tienda.tatuajes.exceptions.ProductoNotFoundException;
import com.idos.apk.backend.tienda.tatuajes.service.interfaces.DetalleOrdenService;
import com.idos.apk.backend.tienda.tatuajes.service.interfaces.OrdenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/orden")
@RequiredArgsConstructor
public class OrdenController {

    private final OrdenService service;
    private final DetalleOrdenService detalleOrdenService;


    @PostMapping("/crear")
    @ResponseStatus(HttpStatus.CREATED)
    public void crear(@RequestBody @Validated OrdenInDto orden) throws UsernameNotFoundException, ProductoNotFoundException {
        String id = service.save(orden).getNumero();
        for (DetalleOrdenInDto detalle : orden.getLista()) {
            detalleOrdenService.save(detalle, id);
        }
    }

    @GetMapping("/mostrar")
    @ResponseStatus(HttpStatus.OK)
    public List<OrdenUserDto> mostrarPorUsuario(@RequestParam String token) throws UsernameNotFoundException {
        List<OrdenUserDto> enviar = new ArrayList<>();
        List<OrdenOutDto> dto = service.getAllByUser(token);
        for (OrdenOutDto out: dto){
            enviar.add(OrdenUserDto.builder()
                    .total(out.getTotal())
                    .fechaCreacion(out.getFechaCreacion())
                    .numero(out.getNumero())
                    .detalles(detalleOrdenService.getAllByOrden(out.getNumero()))
                    .build());
        }
        return enviar;
    }

    @GetMapping("/mostrarTodo")
    @ResponseStatus(HttpStatus.OK)
    public List<OrdenAdminDto> mostrar() {
        List<OrdenAdminDto>enviar = new ArrayList<>();
        List<OrdenOutDto> dto = service.getAll();
        for(OrdenOutDto out: dto){
            enviar.add(OrdenAdminDto.builder()
                    .total(out.getTotal())
                    .fechaCreacion(out.getFechaCreacion())
                    .id_creador(out.getId_creador())
                    .numero(out.getNumero())
                    .detalles(detalleOrdenService.getAllByOrden(out.getNumero()))
                    .build());
        }


        return enviar;
    }

    @GetMapping("/mostrarTodoFecha")
    @ResponseStatus(HttpStatus.OK)
    public List<OrdenOutDto> mostrarPorFecha(@RequestParam("mes")int mes,
                                             @RequestParam("Agno")String agno) {
        return service.getAllByDate(mes, agno);
    }

    @GetMapping("/mostrarId/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<OrdenOutDto> mostrarPorUserId(@PathVariable("id") String id){
       return service.getAllByUserAdmin(id);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") String id) throws OrdenNotFoundException {
        service.delete(id);
    }

    @GetMapping("/tabla")
    @ResponseStatus(HttpStatus.OK)
    public OrdenAgnoDto tabla(){
        String agno = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy"));
        return service.filtroMes(agno);
    }
}
