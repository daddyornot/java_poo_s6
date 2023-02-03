# VSCode tips

(my 20&cent;)

## Declare your JDK

On a typical, standard Debian (or Ubuntu) linux distro, use the following paths for Java 1.8 / Java 11:

```JSONC
/*
 * From VSCode Command Palette (Menu "View" > "Command Palette...", or CTRL+MAJ+P),
 *   invoke action "Preferences: Open User Settings (JSON)"
 *   in order to get this file and customize it as follows:
 */
{
    "java.configuration.runtimes": [
        {
            "name": "JavaSE-11",
            "path": "/usr/lib/jvm/java-11-openjdk-amd64",
            "sources": "/usr/lib/jvm/java-11-openjdk-amd64/lib/src.zip",
            "javadoc": "/usr/share/doc/openjdk-8-doc/api/index.html",
            "default": true
        },
        {
            "name": "JavaSE-1.8",
            "path": "/usr/lib/jvm/java-8-openjdk-amd64",
            "sources": "/usr/lib/jvm/java-8-openjdk-amd64/lib/src.zip",
            "javadoc": "/usr/share/doc/openjdk-8-doc/api/index.html",
            "default": false
        }
    ]
}
/* 
If you don't have local doc packages installed, 
you might be able to use the online doc (not tested):
"javadoc": "https://docs.oracle.com/en/java/javase/11/docs/api/" 

Variants exist; e.g.:
"javadoc": "/usr/share/doc/openjdk-11-jre-headless/"
*/
```
