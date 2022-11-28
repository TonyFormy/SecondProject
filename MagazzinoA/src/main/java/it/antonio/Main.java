package it.antonio;

import java.io.File;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import srv.ArticoloSrv;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// Server web java
        Tomcat tomcat = new Tomcat();

        tomcat.setPort(8080);

        org.apache.catalina.Context ctx = tomcat.addContext("/articolo", new File(".").getAbsolutePath());
        
        ArticoloSrv primaServlet = new ArticoloSrv();
        
        Tomcat.addServlet(ctx, "articolo", primaServlet);

        ctx.addServletMapping("/*", "articolo");

        try {
			tomcat.start();
		} catch (LifecycleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
        tomcat.getServer().await();
	}
		
		
	}


