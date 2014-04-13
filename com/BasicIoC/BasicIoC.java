package com.BasicIoC;

import java.util.HashMap;
import java.util.Map;

/**
 * Class that maps interfaces types to class types and return singleton instances of those class types.
 * @author MarvdKamp
 */
public class BasicIoC {

	/**
	 * Map that contains the mapping of the interface and class types
	 * The mappings are final to ensure that every dependency is available at runtime.
	 */
	private static final Map<Class<? extends IDependency>, Class<?>> mapping = createMapping();
	
	/**
	 * Map that contains the actual interface to instance mapping
	 */
	private static Map<Class<? extends IDependency>, IDependency> objects = new HashMap<Class<? extends IDependency>, IDependency>();	
		
	/**
	 * Get the implementation of a dependency via the interface type
	 * If it is the first time it is requested, create it first
	 * @param tInterface the interface type that the dependency is mapped to
	 * @return An instance of TDependency
	 * @throws IllegalArgumentException when the mapping does not exist
	 * @throws IllegalAccessException when the instantiation of TDependency fails
	 * @throws InstantiationException when the instantiation of TDependency fails
	 */
	public static <TDependency extends IDependency> TDependency getInstance(Class<TDependency> tInterface) throws IllegalArgumentException, 
																										InstantiationException, 
																										IllegalAccessException{
		if(mapping.containsKey(tInterface))
		{
			if(objects.containsKey(tInterface))
			{
				TDependency tInstance = (TDependency) objects.get(tInterface);
				return tInstance;
			}
			else
			{
				Class<?> instanceType = mapping.get(tInterface);
				TDependency tInstance = (TDependency)instanceType.newInstance();
				
				objects.put(tInterface, tInstance);
				return tInstance;
			}
		}
		else
		{
			throw new IllegalArgumentException("Dependency mapping"+ tInterface.getName() +"does not exist");
		}
	}
	
	/**
	 * Define the static mappings between interface and class types
	 * @ Add or Edit when new dependencies become available
	 */
	private static HashMap<Class<? extends IDependency>, Class<?>> createMapping(){
		HashMap<Class<? extends IDependency>, Class<?>> map = new HashMap<Class<? extends IDependency>, Class<?>>();
		
		return map;
	}
}
