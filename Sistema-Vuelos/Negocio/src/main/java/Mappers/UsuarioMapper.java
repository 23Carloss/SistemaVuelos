/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Mappers;

import DTOs.UsuarioDTO;
import POJOs.TipoUsuario;
import POJOs.Usuario;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author $Luis Carlos Manjarrez Gonzalez
 */
public class UsuarioMapper {
    public ReservacionMapper mapper;
    

    public UsuarioMapper() {
        mapper = new ReservacionMapper();
    }
    
    public Usuario convertirAEntity(UsuarioDTO usuario){
       
        var entity = new Usuario();
        entity.set_id(usuario.get_id());
        entity.setNombre(usuario.getNombre());
        entity.setApellidoP(usuario.getApellidoP());
        entity.setApellidoM(usuario.getApellidoM());
        entity.setCorreo(usuario.getCorreo());
        entity.setContrasenia(usuario.getContrasenia());
        entity.setTipoU(usuario.getTipoUsuario().name());
        entity.setReservaciones(mapper.convertirListaAEntity(usuario.getReservaciones()));
        
        return entity;
    }
    public UsuarioDTO convertirADTO(Usuario usuario){
       
        var dto = new UsuarioDTO();
        dto.set_id(usuario.get_id());
        dto.setNombre(usuario.getNombre());
        dto.setApellidoP(usuario.getApellidoP());
        dto.setApellidoM(usuario.getApellidoM());
        dto.setCorreo(usuario.getCorreo());
        TipoUsuario tipoU = TipoUsuario.valueOf(usuario.getTipoU());
        dto.setTipoUsuario(tipoU);
        dto.setContrasenia(usuario.getContrasenia());
        dto.setReservaciones(mapper.convertirListaADTO(usuario.getReservaciones()));
        
        return dto;
    }
    
    public List<UsuarioDTO> ConvertirListaADto(List<Usuario> listaEntity){
        ArrayList<UsuarioDTO> listaUsuarios =  new  ArrayList<>();
        for(Usuario u :listaEntity){
            listaUsuarios.add(convertirADTO(u));
        }
        return listaUsuarios;
        
    
    }
    

}
