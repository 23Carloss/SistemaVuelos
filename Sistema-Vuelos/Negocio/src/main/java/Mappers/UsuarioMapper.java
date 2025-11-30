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
       
        Usuario entity = new Usuario();
        if(usuario.get_id() != null) entity.set_id(usuario.get_id());
        entity.setNombre(usuario.getNombre());
        entity.setApellidoP(usuario.getApellidoP());
        entity.setApellidoM(usuario.getApellidoM());
        entity.setCorreo(usuario.getCorreo());
        entity.setContrasenia(usuario.getContrasenia());
        entity.setTipoUsuario(usuario.getTipoUsuario().name());
        
        return entity;
    }
    public UsuarioDTO convertirADTO(Usuario usuario){
       
        System.out.println("Entitty q lleg  mpper " + usuario);
        UsuarioDTO dto = new UsuarioDTO();
        dto.set_id(usuario.get_id());
        dto.setNombre(usuario.getNombre());
        dto.setApellidoP(usuario.getApellidoP());
        dto.setApellidoM(usuario.getApellidoM());
        dto.setCorreo(usuario.getCorreo());
            TipoUsuario tipoU = TipoUsuario.valueOf(usuario.getTipoUsuario());
        dto.setTipoUsuario(tipoU);
        dto.setContrasenia(usuario.getContrasenia());
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
