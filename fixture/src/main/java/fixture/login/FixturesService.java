package fixture.login;
import java.util.List;

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
}
