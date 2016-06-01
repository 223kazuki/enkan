package enkan.system.devel;

import java.io.File;
import java.util.Collections;

import org.apache.maven.shared.invoker.DefaultInvocationRequest;
import org.apache.maven.shared.invoker.DefaultInvoker;
import org.apache.maven.shared.invoker.InvocationRequest;
import org.apache.maven.shared.invoker.InvocationResult;
import org.apache.maven.shared.invoker.Invoker;
import org.apache.maven.shared.invoker.MavenInvocationException;

import enkan.Env;

/**
 * @author kawasima
 */
public class MavenCompiler implements Compiler {
    @Override
    public InvocationResult execute() throws MavenInvocationException {
        final InvocationRequest request = new DefaultInvocationRequest();
        request.setPomFile(new File("pom.xml"));
        request.setGoals(Collections.singletonList("compile"));

        final Invoker invoker = new DefaultInvoker();
        final File mavenHome = new File(Env.getString("MAVEN_HOME",
                Env.getString("M2_HOME", "/opt/maven")));
        if (!mavenHome.exists()) {
            throw new IllegalStateException("MAVEN_HOME not set");
        }
        invoker.setMavenHome(mavenHome);
        return invoker.execute(request);
    }
}
