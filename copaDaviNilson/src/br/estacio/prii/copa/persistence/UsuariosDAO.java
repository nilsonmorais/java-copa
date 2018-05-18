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
package br.estacio.prii.copa.persistence;

import br.estacio.prii.copa.entidade.Usuarios;
import br.estacio.prii.copa.main.Main;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import org.eclipse.persistence.exceptions.DatabaseException;

public class UsuariosDAO {

    private static final String PERSISTENCE_UNIT_NAME = "copaDaviNilsonPU";
    private static EntityManagerFactory factory;
    private static final Logger LOG = Logger.getLogger(Main.class.getName());
    private final EntityManager em;
    private Usuarios Usuario;

    public UsuariosDAO() throws Exception {
        this(null);
    }

    public UsuariosDAO(Usuarios Usuario) throws Exception {
        try {
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
            em = factory.createEntityManager();
            this.Usuario = Usuario;
        } catch (DatabaseException e) {
            throw new Exception("Erro ao conectar ao banco: " + e.getMessage());
        } catch (PersistenceException e) {
            LOG.severe(e.getMessage());
            throw new Exception("Erro ao conectar ao banco. Verifique se o banco existe e não está aberto.");
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<Usuarios> getAllUsuarios() throws Exception {
        try {
            em.getTransaction().begin();
            Query query = em.createNamedQuery("Usuarios.findAll", Usuarios.class);
            LOG.info(query.toString());
            List results = query.getResultList();
            em.getTransaction().commit();
            LOG.log(Level.INFO, "Resultados: {0}", results.size());
            return results;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<Usuarios> getUsuarioByLogin(String login) {
        em.getTransaction().begin();
        Query query = em.createNamedQuery("USUARIOS.findByLogin", Usuarios.class)
                .setParameter("login", login);
        LOG.info(query.toString());
        List results = query.getResultList();
        em.getTransaction().commit();
        LOG.log(Level.INFO, "Resultados: {0}", results.size());
        return results;
    }

    public void salvarUsuario() throws Exception {
        try {
            em.getTransaction().begin();
            Usuario.checkUsuario();
            em.persist(Usuario);
            em.getTransaction().commit();
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }
}
