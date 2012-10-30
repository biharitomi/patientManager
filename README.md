patientManager
==============
If after clone or pull the jsf or primefaces autocomplete doesn't work, to do:
Right click on gui module->properties->project facets->check in "JavaServer Faces" then apply.
After that you can remove the jsf library from the build path because it is exist on the maven dependencies.