package com.big.tuwien.smartmatcher.strategy.sreuse;



import java.io.File;
import java.io.FileInputStream;
import java.net.URI;

import com.sleepycat.dbxml.XmlException;
import com.sleepycat.dbxml.XmlInputStream;
import com.sleepycat.dbxml.XmlManager;
import com.sleepycat.dbxml.XmlResolver;
import com.sleepycat.dbxml.XmlResults;
import com.sleepycat.dbxml.XmlTransaction;
import com.sleepycat.dbxml.XmlValue;

public class MyResolver extends XmlResolver {
	
    public MyResolver() throws XmlException {}

    
    public boolean resolveDocument(XmlTransaction txn, XmlManager mgr,
                                   String uri, XmlValue val)
        throws XmlException {
        return false;
    }
    
    
    public boolean resolveCollection(XmlTransaction txn, XmlManager mgr,
                                     String uri, XmlResults res)
        throws XmlException {
        return false;
    }
    
    
    public XmlInputStream resolveSchema(XmlTransaction txn, XmlManager mgr,
                                        String location, String nameSpace)
        throws XmlException {
        return null;
    }
    
    
    public XmlInputStream resolveEntity(XmlTransaction txn, XmlManager mgr,
                                        String systemId, String publicId)
        throws XmlException {
        return null;
    }
    
    
    public boolean resolveModuleLocation(XmlTransaction txn, XmlManager mgr,
                                         String nameSpace, XmlResults result)
        throws XmlException {
		  return false;
    }
    
    
    public XmlInputStream resolveModule(XmlTransaction txn, XmlManager mgr,
                                        String moduleLocation, String nameSpace)
        throws XmlException {
   
    	try {
    		// URI resolving
			URI uri = new URI(moduleLocation);
			FileInputStream fin = new FileInputStream(new File(uri));
			return mgr.createInputStream(fin);
		} catch (Exception e) {
			// not an URI -> use default path
			/* desktop path */
			String path = "/home/alex/ecworkspace/SReuse/" + moduleLocation;
			/* notebook path */
			path = "/home/alex/dev/ecworkspace_modelling/SReuse/" + moduleLocation;
	    	return mgr.createLocalFileInputStream(path);
		}
    }
}


