package clinica.test.acciones;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ UsuarioLoginTest.class, PacienteBuscarTest.class, PacienteObtenerConsultasTest.class,
		ConsultaConfirmarTest.class, PacienteObtenerUltimaConsulta.class })
public class AllTests {
}
