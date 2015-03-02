package fixture.login;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.fixturescripts.FixtureResult;
import org.apache.isis.applib.fixturescripts.FixtureScript;
import org.apache.isis.applib.fixturescripts.FixtureScripts;
import org.apache.isis.applib.fixturescripts.SimpleFixtureScript;

@Named("Datos")
@DomainService(menuOrder = "200")
public class FixturesService extends FixtureScripts {

	
    public FixturesService() {
        super("fixture");
    }
    @Hidden
    public FixtureScript default0RunFixtureScript() {
        return findFixtureScriptFor(SimpleFixtureScript.class);
    }
    @Hidden
    @Override
    public List<FixtureScript> choices0RunFixtureScript() {
        return super.choices0RunFixtureScript();
    }
    @Hidden
    @MemberOrder(sequence="20")
    @Programmatic
    public Object instalarRoles() {
        final List<FixtureResult> run = findFixtureScriptFor(RolesFixture.class).run(null);
        return run.get(0).getObject();
    }
    @Hidden
    @MemberOrder(sequence="30")
    @Programmatic
    public Object instalarPermisos() {
        final List<FixtureResult> run = findFixtureScriptFor(PermisosFixture.class).run(null);
        return run.get(0).getObject();
    }
    @Hidden
    @MemberOrder(sequence="40")
    @Programmatic
    public Object instalarProvincias() {
        final List<FixtureResult> run = findFixtureScriptFor(ProvinciasFixture.class).run(null);
        return run.get(0).getObject();
    }
    @Hidden
    @MemberOrder(sequence="50")
    @Programmatic
    public Object instalarDepartamentos() {
        final List<FixtureResult> run = findFixtureScriptFor(DepartamentosFixture.class).run(null);
        return run.get(0).getObject();
    }
    @Hidden
    @MemberOrder(sequence="60")
    @Programmatic
    public Object instalarLocalidades() {
        final List<FixtureResult> run = findFixtureScriptFor(LocalidadesFixture.class).run(null);
        return run.get(0).getObject();
    }
    @Hidden
    @MemberOrder(sequence="70")
    @Programmatic
    public Object instalarEstablecimientoDePrueba() {
        final List<FixtureResult> run = findFixtureScriptFor(EstablecimientoDePruebaFixture.class).run(null);
        return run.get(0).getObject();
    }
    @Hidden
    @MemberOrder(sequence="80")
    @Programmatic
    public Object instalarPersonaDePrueba() {
        final List<FixtureResult> run = findFixtureScriptFor(PersonaDePruebaFixture.class).run(null);
        return run.get(0).getObject();
    }
    @Hidden
    @MemberOrder(sequence="90")
    @Programmatic
    public Object instalarCuentaDePrueba() {
        final List<FixtureResult> run = findFixtureScriptFor(CuentaDePruebaFixture.class).run(null);
        return run.get(0).getObject();
    }
    @Hidden
    @MemberOrder(sequence="100")
    @Programmatic
    public Object instalarServidoresDeMail() {
        final List<FixtureResult> run = findFixtureScriptFor(ServidoresDeMailFixture.class).run(null);
        return run.get(0).getObject();
    }
    @Hidden
    @MemberOrder(sequence="110")
    @Programmatic
    public Object instalarPaises() {
        final List<FixtureResult> run = findFixtureScriptFor(PaisesFixture.class).run(null);
        return run.get(0).getObject();
    }
    @Hidden
    @MemberOrder(sequence="120")
    @Programmatic
    public Object instalarCargos() {
        final List<FixtureResult> run = findFixtureScriptFor(CargosFixture.class).run(null);
        return run.get(0).getObject();
    }
    @MemberOrder(sequence="130")
    @Programmatic
    public Object instalarDatosDePrueba() {
        final List<FixtureResult> run = findFixtureScriptFor(DatosPruebaFixture.class).run(null);
        return run.get(0).getObject();
    }
    
    @Hidden
    @PostConstruct //Hace que se ejecute al inicio
    @Override //se sobre escribe el metodo por eso hay que llamarlo otra vez para que no pinche
    public void init()
    {
    	super.init();//se llama al metodo init heredado
    	this.instalarRoles();
    	this.instalarProvincias();
    	this.instalarDepartamentos();
    	this.instalarPermisos();
        this.instalarLocalidades();
        this.instalarEstablecimientoDePrueba();
        this.instalarPersonaDePrueba();
        this.instalarCuentaDePrueba();
        this.instalarServidoresDeMail();
        this.instalarCargos();
        this.instalarPaises();
    }
}