/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clinica.test.acciones;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import clinica.acciones.AccionesUsuario;
import clinica.beans.Medico;
import clinica.beans.Paciente;
import clinica.beans.Usuario;

/**
 *
 * @author Sebastian Lopez
 */
public class UsuarioLoginTest {

    public UsuarioLoginTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //

    @Test
    public void validaNotNull() throws Exception {
        AccionesUsuario acciones = new AccionesUsuario();

        Usuario elQueSeConecta = acciones.login("1-9", "123");

        assertTrue(elQueSeConecta != null);
    }

    @Test
    public void validaSiEsMedico() throws Exception {
        AccionesUsuario acciones = new AccionesUsuario();

        Usuario elQueSeConecta = acciones.login("1-9", "123");

        assertTrue(elQueSeConecta instanceof Medico);
    }

    @Test
    public void validaSiEsPaciente() throws Exception {
         AccionesUsuario acciones = new AccionesUsuario();

        Usuario elQueSeConecta = acciones.login("1-7", "123");

        assertTrue(elQueSeConecta instanceof Paciente);
    }
 
    @Test
    public void validaSiEsPaciente2() throws Exception {
         AccionesUsuario acciones = new AccionesUsuario();

        Usuario elQueSeConecta = acciones.login("1-6", "123");

        assertTrue(elQueSeConecta instanceof Paciente);
    }
    @Test
    public void validaSiUsuarioNoExiste() throws Exception {
         AccionesUsuario acciones = new AccionesUsuario();

        Usuario elQueSeConecta = acciones.login("xxx", "123");

        assertTrue(elQueSeConecta == null);
    }

    @Test
    public void validaRutNull() throws Exception {
        AccionesUsuario acciones = new AccionesUsuario();

        Usuario elQueSeConecta = acciones.login(null,"123");

        assertTrue(elQueSeConecta == null);
    }

    @Test
    public void validaPassNull() throws Exception {
        AccionesUsuario acciones = new AccionesUsuario();

        Usuario elQueSeConecta = acciones.login("1-8", null);

        assertTrue(elQueSeConecta == null);
    }

    @Test
    public void validaRutYPassNull() throws Exception {
        AccionesUsuario acciones = new AccionesUsuario();

        Usuario elQueSeConecta = acciones.login(null,null);

        assertTrue(elQueSeConecta == null);
    }
}