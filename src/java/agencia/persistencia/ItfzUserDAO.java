/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package agencia.persistencia;

import agencia.modelo.Usuario;
import java.util.List;

/**
 *
 * @author ESa10969
 */
public interface ItfzUserDAO {
    public Usuario buscarPorUsername(String username);
}
