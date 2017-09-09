package com.ab;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import com.ab.annoation.Controller;
import com.ab.annoation.ControllerAction;
import com.ab.annoation.View;
import com.ab.annoation.ViewAction;
import com.ab.common.ClassFinder;

public class Handler {

	public static void handle(String action) throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		String controllerName = action.contains(".") ? action.split("\\.")[0] : "Main";
		String methodName = action.contains(".") ? action.split("\\.")[1] : action;
		Class<?> cls = getControllerClsByAnnoName(controllerName);

		Object controller = cls.getMethod("getInstance").invoke(null);

		Method method = getControllerMethodByAnnoName(methodName, cls);
		ModelAndView modelAndView = (ModelAndView) method.invoke(controller);

		if (modelAndView == null) {
			return;
		}

		String viewName = modelAndView.getViewName().contains(".") ? modelAndView.getViewName().split("\\.")[0]
				: "Main";
		String viewMethodName = modelAndView.getViewName().contains(".") ? modelAndView.getViewName().split("\\.")[1]
				: modelAndView.getViewName();

		Class<?> viewCls = getViewClsByAnnoName(viewName);
		Object view = viewCls.getMethod("getInstance").invoke(null);

		Method viewMethod = getViewMethodByAnnoName(viewMethodName, viewCls);
		viewMethod.invoke(view, modelAndView);
	}
	
	public static void handleView(String action, Object obj) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		String viewName = action.contains(".") ? action.split("\\.")[0] : "Main";
		String viewMethodName = action.contains(".") ? action.split("\\.")[1] : action;

		Class<?> viewCls = getViewClsByAnnoName(viewName);
		Object view = viewCls.getMethod("getInstance").invoke(null);
		
		ModelAndView modelAndView = new ModelAndView(action, obj);

		Method viewMethod = getViewMethodByAnnoName(viewMethodName, viewCls);
		viewMethod.invoke(view, modelAndView);
	}

	public static Class<?> getControllerClsByAnnoName(String annoationName) {

		List<Class<?>> classes = ClassFinder.find("com.ab.controller");
		for (Class<?> cls : classes) {
			Controller contAnno = cls.getAnnotation(Controller.class);
			if (contAnno != null && contAnno.name() != null && contAnno.name().equalsIgnoreCase(annoationName)) {
				return cls;
			}
		}

		return null;
	}

	public static Method getControllerMethodByAnnoName(String annoationName, Class<?> cls) {

		Method[] methods = cls.getMethods();
		for (Method method : methods) {
			ControllerAction contAction = method.getAnnotation(ControllerAction.class);
			if (contAction != null && contAction.action() != null
					&& contAction.action().equalsIgnoreCase(annoationName)) {
				return method;
			}
		}

		return null;
	}

	public static Class<?> getViewClsByAnnoName(String annoationName) {

		List<Class<?>> classes = ClassFinder.find("com.ab.view");
		for (Class<?> cls : classes) {
			View view = cls.getAnnotation(View.class);
			if (view != null && view.name() != null && view.name().equalsIgnoreCase(annoationName)) {
				return cls;
			}
		}

		return null;
	}

	public static Method getViewMethodByAnnoName(String annoationName, Class<?> cls) {

		Method[] methods = cls.getMethods();
		for (Method method : methods) {
			ViewAction viewAction = method.getAnnotation(ViewAction.class);
			if (viewAction != null && viewAction.action() != null
					&& viewAction.action().equalsIgnoreCase(annoationName)) {
				return method;
			}
		}

		return null;
	}

}
