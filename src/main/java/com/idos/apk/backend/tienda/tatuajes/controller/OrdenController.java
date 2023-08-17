package com.idos.apk.backend.tienda.tatuajes.controller;

import com.idos.apk.backend.tienda.tatuajes.exceptions.OrdenNotFoundException;
import com.idos.apk.backend.tienda.tatuajes.exceptions.ProductoNotFoundException;
import com.idos.apk.backend.tienda.tatuajes.model.dto.detalleorden.DetalleOrdenDto;
import com.idos.apk.backend.tienda.tatuajes.model.dto.orden.*;
import com.idos.apk.backend.tienda.tatuajes.service.interfaces.DetalleOrdenService;
import com.idos.apk.backend.tienda.tatuajes.service.interfaces.OrdenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/orden")
@CrossOrigin(origins = "*")
public class OrdenController {

    private final OrdenService service;
    private final DetalleOrdenService detalleOrdenService;


    public OrdenController(OrdenService service, DetalleOrdenService detalleOrdenService) {
        this.service = service;
        this.detalleOrdenService = detalleOrdenService;
    }

    @PostMapping("/crear")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity crear(@RequestBody @Validated OrdenDtoIn orden) throws UsernameNotFoundException, ProductoNotFoundException {
        String id = service.save(orden).numero();
        for (DetalleOrdenDto detalle : orden.lista()) {
            detalleOrdenService.save(detalle, id);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @GetMapping("/mostrar")
    @ResponseStatus(HttpStatus.OK)
    public List<OrdenUser> mostrarPorUsuario(@RequestParam String token) throws UsernameNotFoundException {
        List<OrdenUser> enviar = new ArrayList<>();
        List<OrdenDtoOut> dto = service.getAllByUser(token);
        for (OrdenDtoOut out: dto){
            enviar.add(OrdenUser.builder()
                    .total(out.total())
                    .fechaCreacion(out.fechaCreacion())
                    .numero(out.numero())
                    .detalles(detalleOrdenService.getAllByOrden(out.numero()))
                    .build());
        }
        return enviar;
    }

    @GetMapping("/mostrarTodo")
    @ResponseStatus(HttpStatus.OK)
    public List<OrdenAdmin> mostrar() {
        List<OrdenAdmin>enviar = new ArrayList<>();
        List<OrdenDtoOut> dto = service.getAll();
        for(OrdenDtoOut out: dto){
            enviar.add(OrdenAdmin.builder()
                    .total(out.total())
                    .fechaCreacion(out.fechaCreacion())
                    .id_creador(out.id_creador())
                    .numero(out.numero())
                    .detalles(detalleOrdenService.getAllByOrden(out.numero()))
                    .build());
        }


        return enviar;
    }

    @GetMapping("/mostrarTodoFecha")
    @ResponseStatus(HttpStatus.OK)
    public List<OrdenDtoOut> mostrarPorFecha(@RequestParam("mes")int mes,
                                             @RequestParam("Agno")String agno) {
        return service.getAllByDate(mes, agno);
    }

    @GetMapping("/mostrarId/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<OrdenDtoOut> mostrarPorUserId(@PathVariable("id") String id){
       return service.getAllByUserAdmin(id);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") String id) throws OrdenNotFoundException {
        service.delete(id);
    }

    @GetMapping("/tabla")
    @ResponseStatus(HttpStatus.OK)
    public OrdenPorAgno tabla(){
        String agno = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy"));
        return service.filtroMes(agno);
    }
}
