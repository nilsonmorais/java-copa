/*
This is free and unencumbered software released into the public domain.

Anyone is free to copy, modify, publish, use, compile, sell, or
distribute this software, either in source code form or as a compiled
binary, for any purpose, commercial or non-commercial, and by any
means.

In jurisdictions that recognize copyright laws, the author or authors
of this software dedicate any and all copyright interest in the
software to the public domain. We make this dedication for the benefit
of the public at large and to the detriment of our heirs and
successors. We intend this dedication to be an overt act of
relinquishment in perpetuity of all present and future rights to this
software under copyright law.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
IN NO EVENT SHALL THE AUTHORS BE LIABLE FOR ANY CLAIM, DAMAGES OR
OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
OTHER DEALINGS IN THE SOFTWARE.

For more information, please refer to <http://unlicense.org>
 */
package br.estacio.prii.copa.main;

import br.estacio.prii.copa.entidade.Estadios;
import br.estacio.prii.copa.entidade.Usuarios;
import br.estacio.prii.copa.gui.Principal;
import br.estacio.prii.copa.utils.Utils;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;


public class Main {

    private static final Logger LOG = Logger.getLogger(Main.class.getName());
    
    public static void main(String[] args) throws SQLException, Exception {

        try {
//            List<Usuarios> e = Usuarios.getAll();
//            for (Usuarios Usuario : e) {
//                LOG.log(Level.INFO, "Registro: {0}", Usuario.getNome());
//            }
            
//            Criar Usuario
//            Usuarios novo = new Usuarios("Nilson","Nilson","nilson@nilson.com","123");
//            novo.Save();
//            Criar Estadio
//            Estadios novo = new Estadios(350, "Estadio Teste2", "Piaui");
//            novo.Save();

//            Atualizar Usuario
//            Usuarios user = u.getUsuarioByLogin("Nilson");
//            user.setCelular("222");
//            if (user.Update()){
//                Utils.showDialog("Usuário Atualizado!");
//            }  

//            Remover Usuario
//            Usuarios user = u.getUsuarioByLogin("s");
//            if (user.Delete()){
//                Utils.showDialog("Usuário Removido!");
//            }                    
            
        } catch (Exception exception) {
            LOG.severe(exception.getMessage());
            Utils.showDialog(exception.getMessage());
        }
    }

}
