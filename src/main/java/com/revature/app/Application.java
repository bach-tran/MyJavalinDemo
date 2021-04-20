package com.revature.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.controller.Controller;
import com.revature.controller.ExceptionController;
import com.revature.controller.PirateController;
import com.revature.controller.ShipController;
import com.revature.controller.StaticFileController;

import io.javalin.Javalin;

public class Application {

	private static Logger logger = LoggerFactory.getLogger(Application.class);
	
	public static void main(String[] args) {
		
		Javalin app = Javalin.create();
		
		app.before(ctx -> {
			String URI = ctx.req.getRequestURI();
			String httpMethod = ctx.req.getMethod();
			logger.info(httpMethod + " request to endpoint " + URI + " received");
		});
		
		mapControllers(app, new PirateController(), new ExceptionController(), 
				new StaticFileController(), new ShipController());
		
		app.start(7001);
		
	}
	
	public static void mapControllers(Javalin app, Controller... controllers) {
		for (Controller c : controllers) {
			c.mapEndpoints(app);
		}
	}

}
