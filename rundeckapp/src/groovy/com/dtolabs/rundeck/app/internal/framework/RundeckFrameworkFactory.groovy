package com.dtolabs.rundeck.app.internal.framework

import com.dtolabs.rundeck.core.common.FilesystemFramework
import com.dtolabs.rundeck.core.common.Framework
import com.dtolabs.rundeck.core.common.FrameworkFactory
import com.dtolabs.rundeck.core.common.ProjectManager
import com.dtolabs.rundeck.core.utils.IPropertyLookup
import org.apache.log4j.Logger

/**
 * Created by greg on 2/20/15.
 */
class RundeckFrameworkFactory {
    public static final String FILESYSTEM = 'filesystem'
    public static final String DB = 'db'
    public static final Set<String> STORAGE_TYPES=Collections.unmodifiableSet([FILESYSTEM,DB] as Set)
    public static final Logger logger = Logger.getLogger(RundeckFrameworkFactory)
    FilesystemFramework frameworkFilesystem
    String type
    ProjectManager dbProjectManager
    ProjectManager filesystemProjectManager
    IPropertyLookup propertyLookup
    Framework createFramework() {
        if (type == FILESYSTEM) {
            logger.info("Creating Filesystem project manager")
            return FrameworkFactory.createFramework(propertyLookup, frameworkFilesystem, filesystemProjectManager)
        } else if (type == DB) {
            logger.info("Creating DB project manager")
            return FrameworkFactory.createFramework(propertyLookup, frameworkFilesystem, dbProjectManager)
        } else {
            throw new IllegalArgumentException("unsupported type: " + type + ", expected one of: " + STORAGE_TYPES)
        }
    }
}
