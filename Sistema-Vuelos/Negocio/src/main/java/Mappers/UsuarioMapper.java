/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Mappers;

import DTOs.UsuarioDTO;
import POJOs.Usuario;

/**
 *
 * @author $Luis Carlos Manjarrez Gonzalez
 */
public class UsuarioMapper {
    public ReservacionMapper mapper;
    

    public UsuarioMapper() {
        mapper = new ReservacionMapper();
    }
    
    public Usuario convertirADTO(UsuarioDTO usuario){
        var entity = new Usuario();
        entity.setObjectID(usuario.getId());
        entity.setNombre(usuario.getNombre());
        entity.setApellidoP(usuario.getApellidoP());
        entity.setApellidoM(usuario.getApellidoM());
        entity.setCorreo(usuario.getCorreo());
        entity.setContrasenia(usuario.getContrasenia());
        entity.setReservaciones(mapper.convertirListaAEntity(usuario.getReservaciones()));
        return entity;
    }
    public UsuarioDTO convertirADTO(Usuario usuario){
        var entity = new UsuarioDTO();
        entity.setId(usuario.getId());
        entity.setNombre(usuario.getNombre());
        entity.setApellidoP(usuario.getApellidoP());
        entity.setApellidoM(usuario.getApellidoM());
        entity.setCorreo(usuario.getCorreo());
        entity.setContrasenia(usuario.getContrasenia());
        entity.setReservaciones(mapper.convertirListaADTO(usuario.getReservaciones()));
        return entity;
    }
    

}
