/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SEEDERS;

import BOs.UsuarioBO;
import Config.MongoClientProvider;
import DTOs.UsuarioDTO;
import POJOs.TipoUsuario;
import java.util.Arrays;
import java.util.List;

/**
 *
 *  ADMINS:
 *  admin1@correo.com  ps:admin123
 *  admin2@correo.com  ps:rootpass
 *  admin3@correo.com  ps:adminpass
 *  
 * 
 * 
 * 
 * 
 * 
 * 
 * @author Jesus Gammael Soto Escalante 248336
 */
public class SEEDERS_2 {

    public static void main(String[] args) {
        MongoClientProvider.INSTANCE.init();
        UsuarioBO usuarioBO = new UsuarioBO();

        try {
            List<UsuarioDTO> usuarios = Arrays.asList(
                    new UsuarioDTO("admin1@correo.com", "admin123", "Carlos", "Ramírez", "López", TipoUsuario.administrador),
                    new UsuarioDTO("user1@correo.com", "usuario123", "María", "González", "Hernández", TipoUsuario.usuario),
                    new UsuarioDTO("user2@correo.com", "clave456", "José", "Martínez", "Pérez", TipoUsuario.usuario),
                    new UsuarioDTO("user3@correo.com", "pass789", "Ana", "Torres", "Sánchez", TipoUsuario.usuario),
                    new UsuarioDTO("admin2@correo.com", "rootpass", "Lucía", "Fernández", "Morales", TipoUsuario.administrador),
                    new UsuarioDTO("user4@correo.com", "segura12", "Miguel", "Castro", "Jiménez", TipoUsuario.usuario),
                    new UsuarioDTO("user5@correo.com", "contraseña", "Laura", "Vega", "Domínguez", TipoUsuario.usuario),
                    new UsuarioDTO("user6@correo.com", "clave999", "Andrés", "Silva", "Reyes", TipoUsuario.usuario),
                    new UsuarioDTO("user7@correo.com", "pass777", "Patricia", "Ruiz", "Aguilar", TipoUsuario.usuario),
                    new UsuarioDTO("admin3@correo.com", "adminpass", "Roberto", "Moreno", "Delgado", TipoUsuario.administrador)
            );

            for (UsuarioDTO u : usuarios) {
                usuarioBO.crearUsuario(u);
            }

            System.out.println("Usuarios insertados correctamente");

        } catch (Exception e) {
            System.err.println("Error al insertar usuarios: " + e.getMessage());
        }

    }
}
