// IPPO Assignment 1, Version 20.3, 21/12/2020
package ippo.assignment1.proxy;

import ippo.assignment1.library.Picture;
import ippo.assignment1.library.service.Service;
import ippo.assignment1.library.service.ServiceFromProperties;
import ippo.assignment1.library.utils.HashMap;


/**
 * A skeleton cache proxy service for the PictureViewer application.
 * The skeleton does no implement any caching!
 *
 * @author  Paul Anderson &lt;dcspaul@ed.ac.uk&gt;
 * @version 20.3, 21/12/2020
 */
public class CacheProxy implements Service {

	private Service baseService = null;
	// create a HashMap to save the identifier of picture and the picture itself
	private HashMap<String,Picture> cache = new HashMap<String,Picture>();
	/**
	 * Return a textual name for the service.
	 *
	 * @return the name of the base service
	 */
	public String serviceName() {
		return baseService.serviceName();
	}

	/**
	 * Create a proxy service based on the service specified in the
	 * <code>proxy.cache.service</code> resource.
	 */
	public CacheProxy() {
		baseService = new ServiceFromProperties("proxy.cache.service");
	}

	/**
	 * Create a proxy service based on the specified service.
	 *
	 * @param baseService the base service
	 */
	public CacheProxy(Service baseService) {
		this.baseService = baseService;
	}
	/**
	 * Return a picture from the base service.
	 *
	 * @param subject the free-text subject string
	 * @param index the index of the matching picture to return
	 * @return the requested picture
	 */
	public Picture getPicture(String subject, int index) {
		//take the combination of subject and index as a single key
		String subject_index = subject + index;
		//check if the cache includes current parameters(subject and index)
		if(cache.containsKey(subject + index)){
			System.out.println("Get Picture from cache");
			return cache.get(subject + index);
		}
		// store the picture in the cache to make the system faster
		cache.put(subject_index, baseService.getPicture(subject,index));
		System.out.println("Get picture from Internet");
		return cache.get(subject_index);
	}
}