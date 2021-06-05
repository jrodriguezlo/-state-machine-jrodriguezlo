package com.imatia.statemachine.statemachine;

import java.util.EnumSet;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.transition.Transition;

import com.imatia.statemachine.events.Eventos;
import com.imatia.statemachine.model.Order;
import com.imatia.statemachine.states.Estados;

import jdk.internal.org.jline.utils.Log;

@Configuration
@EnableStateMachine
public class StateMachine extends EnumStateMachineConfigurerAdapter<Estados, Eventos> {

	@Bean
	public StateMachineListener<Estados, Eventos> listener(Order order) {
		return new StateMachineListenerAdapter<Estados, Eventos>() {			
			
		};
	}

	@Override
	public void configure(StateMachineConfigurationConfigurer<Estados, Eventos> config)
			throws Exception {
		config
			.withConfiguration()
				.autoStartup(true);		
	}
	
	@Override
	public void configure(StateMachineStateConfigurer<Estados, Eventos> states) throws Exception {
		states
			.withStates()
				.initial(Estados.RECOGIDO_EN_ALMACEN)
				.states(EnumSet.allOf(Estados.class))
			.and()
			.withStates()
				.initial(Estados.EN_REPARTO)
				.states(EnumSet.of(Estados.ENTREGADO, Estados.INCIDENCIA_EN_ENTREGA))
			.and()
			.withStates()
				.initial(Estados.INCIDENCIA_EN_ENTREGA)
				.states(EnumSet.of(Estados.ENTREGADO, Estados.ENTREGADO))
			.and()
			.withStates()
				.initial(Estados.ENTREGADO)
				.end(Estados.ENTREGADO)
		;
	}
	
	@Override
	public void configure(StateMachineTransitionConfigurer<Estados, Eventos> transitions) throws Exception {
	    transitions
	    	//Transiciones partiendo del estado "RECOGIDO_EN_ALMACEN"
	    	.withExternal()
	    		.source(Estados.RECOGIDO_EN_ALMACEN)
	    		.target(Estados.EN_REPARTO)
	    		.event(Eventos.RECOGIDO_REPARTO)
	    		.action(action())
	    	.and()
	    	.withExternal()
    			.source(Estados.RECOGIDO_EN_ALMACEN)
    			.target(Estados.INCIDENCIA_EN_ENTREGA)
    			.event(Eventos.RECOGIDO_INCIDENCIA)
    			.action(action())
    		.and()
    		.withExternal()
				.source(Estados.RECOGIDO_EN_ALMACEN)
				.target(Estados.ENTREGADO)
				.event(Eventos.RECOGIDO_ENTREGADO)
				.action(action())
			.and()
			//Transiciones partiendo del estado "EN_REPARTO"
			.withExternal()
				.source(Estados.EN_REPARTO)
				.target(Estados.INCIDENCIA_EN_ENTREGA)
				.event(Eventos.REPARTO_INCIDENCIA)
				.action(action())
			.and()
			.withExternal()
				.source(Estados.EN_REPARTO)
				.target(Estados.ENTREGADO)
				.event(Eventos.REPARTO_ENTREGADO)
				.action(action())
			.and()
			//Transiciones partiendo del estado "INCIDENCIA_EN_ENTREGA"
			.withExternal()
				.source(Estados.INCIDENCIA_EN_ENTREGA)
				.target(Estados.EN_REPARTO)
				.event(Eventos.INCIDENCIA_REPARTO)
				.action(action())
			.and()
			.withExternal()
				.source(Estados.INCIDENCIA_EN_ENTREGA)
				.target(Estados.ENTREGADO)
				.event(Eventos.INCIDENCIA_ENTREGADO)
				.action(action())
			.and()
			;

	}
	
	@Bean
	public Action<Estados,Eventos> action(){
		return new Action<Estados, Eventos>() {

			@Override
			public void execute(StateContext<Estados, Eventos> context) {
				Log.info(context.getSource()+" pasa al estado: "+ context.getTarget());
			}

		};
		
		
	}
}
