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

import br.estacio.prii.copa.entidade.Estadios;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class EstadiosDAO {

    private static final Logger LOG = Logger.getLogger(DAO.class.getName());
    private Estadios Estadio;
    private DAO DAO;
    private EntityManager em;

    public EstadiosDAO() throws Exception {
        this(null);
    }

    public EstadiosDAO(Estadios Estadio) throws Exception {
        if (em == null){
            this.em = DAO.getInstance().getEntityManager();
            this.DAO = DAO.getInstance();
        }
        this.Estadio = Estadio;
    }

    public static List<Estadios> getAllEstadios() throws Exception {
        try {
            EntityManager em = br.estacio.prii.copa.persistence.DAO.getInstance().getEntityManager();
            em.getTransaction().begin();
            Query query = em.createNamedQuery("Estadios.findAll", Estadios.class);
            LOG.info(query.toString());
            List results = query.getResultList();
            em.getTransaction().commit();
            return results;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public Estadios getEstadioByName(String name) throws Exception {
        try {
            em.getTransaction().begin();
            Query query = em.createNamedQuery("Estadios.findByName", Estadios.class)
                    .setParameter("nome", name);
            LOG.info(query.toString());
            List results = query.getResultList();
            em.getTransaction().commit();
            return (Estadios) results.get(0);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void saveEstadio() throws Exception {
        try {
            Estadio.checkEstadio();
            DAO.save(Estadio);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public void updateEstadio() throws Exception {
        try {
            Estadio.checkEstadio();
            DAO.update(Estadio);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public void deleteEstadio() throws Exception {
        try {
            if (Estadio.getId() == null) {
                throw new Exception("Não é possivel remover um registro sem ID.");
            }
            DAO.delete(Estadio);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }
}
