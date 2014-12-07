package fixture.login;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.fixturescripts.FixtureResult;
import org.apache.isis.applib.fixturescripts.FixtureScript;
import org.apache.isis.applib.fixturescripts.FixtureScripts;
import org.apache.isis.applib.fixturescripts.SimpleFixtureScript;

@Named("Prototyping")
@DomainService(menuOrder = "200")
public class FixturesService extends FixtureScripts {

    public FixturesService() {
        super("fixture");
    }
    
    public FixtureScript default0RunFixtureScript() {
        return findFixtureScriptFor(SimpleFixtureScript.class);
    }
    
    @Override
    public List<FixtureScript> choices0RunFixtureScript() {
        return super.choices0RunFixtureScript();
    }
    

    @Programmatic
    @MemberOrder(sequence="20")
    public Object instalarRoles() {
        final List<FixtureResult> run = findFixtureScriptFor(RolesFixture.class).run(null);
        return run.get(0).getObject();
    }
    
    @Programmatic
    @MemberOrder(sequence="30")
    public Object instalarPermisos() {
        final List<FixtureResult> run = findFixtureScriptFor(PermisosFixture.class).run(null);
        return run.get(0).getObject();
    }
    
    @Programmatic
    @MemberOrder(sequence="40")
    public Object instalarProvincias() {
        final List<FixtureResult> run = findFixtureScriptFor(ProvinciasFixture.class).run(null);
        return run.get(0).getObject();
    }
    
    @Programmatic
    @MemberOrder(sequence="50")
    public Object instalarDepartamentos() {
        final List<FixtureResult> run = findFixtureScriptFor(DepartamentosFixture.class).run(null);
        return run.get(0).getObject();
    }
    
    @Programmatic
    @MemberOrder(sequence="60")
    public Object instalarLocalidades() {
        final List<FixtureResult> run = findFixtureScriptFor(LocalidadesFixture.class).run(null);
        return run.get(0).getObject();
    }

    @Programmatic
    @MemberOrder(sequence="70")
    public Object instalarEstablecimientoDePrueba() {
        final List<FixtureResult> run = findFixtureScriptFor(EstablecimientoDePruebaFixture.class).run(null);
        return run.get(0).getObject();
    }
    
    @Programmatic
    @MemberOrder(sequence="80")
    public Object instalarPersonaDePrueba() {
        final List<FixtureResult> run = findFixtureScriptFor(PersonaDePruebaFixture.class).run(null);
        return run.get(0).getObject();
    }
    
    @Programmatic
    @MemberOrder(sequence="90")
    public Object instalarCuentaDePrueba() {
        final List<FixtureResult> run = findFixtureScriptFor(CuentaDePruebaFixture.class).run(null);
        return run.get(0).getObject();
    }
    
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
    }
}
