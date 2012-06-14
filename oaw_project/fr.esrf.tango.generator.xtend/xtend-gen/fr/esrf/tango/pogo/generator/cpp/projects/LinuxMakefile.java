package fr.esrf.tango.pogo.generator.cpp.projects;

import com.google.inject.Inject;
import fr.esrf.tango.pogo.generator.cpp.global.InheritanceUtils;
import fr.esrf.tango.pogo.generator.cpp.global.StringUtils;
import fr.esrf.tango.pogo.pogoDsl.AdditionalFile;
import fr.esrf.tango.pogo.pogoDsl.ClassDescription;
import fr.esrf.tango.pogo.pogoDsl.Inheritance;
import fr.esrf.tango.pogo.pogoDsl.PogoDeviceClass;
import fr.esrf.tango.pogo.pogoDsl.Preferences;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.generator.IGenerator;
import org.eclipse.xtext.xbase.lib.IntegerExtensions;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xtend2.lib.ResourceExtensions;

@SuppressWarnings("all")
public class LinuxMakefile implements IGenerator {
  @Inject
  private StringUtils _stringUtils;
  
  @Inject
  private InheritanceUtils _inheritanceUtils;
  
  public void doGenerate(final Resource resource, final IFileSystemAccess fsa) {
    Iterable<EObject> _allContentsIterable = ResourceExtensions.allContentsIterable(resource);
    Iterable<PogoDeviceClass> _filter = IterableExtensions.<PogoDeviceClass>filter(_allContentsIterable, fr.esrf.tango.pogo.pogoDsl.PogoDeviceClass.class);
    for (final PogoDeviceClass cls : _filter) {
      CharSequence _generateLinuxMakefile = this.generateLinuxMakefile(cls);
      fsa.generateFile("Makefile", _generateLinuxMakefile);
    }
  }
  
  public CharSequence generateLinuxMakefile(final PogoDeviceClass cls) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("#PROTECTED REGION ID(");
    String _name = cls.getName();
    _builder.append(_name, "");
    _builder.append("::Makefile) ENABLED START#");
    _builder.newLineIfNotEmpty();
    _builder.append("#=============================================================================");
    _builder.newLine();
    _builder.append("#");
    _builder.newLine();
    _builder.append("# file :        Makefile");
    _builder.newLine();
    _builder.append("#");
    _builder.newLine();
    _builder.append("# description : Makefile to generate a TANGO device server.");
    _builder.newLine();
    _builder.append("#");
    _builder.newLine();
    _builder.append("# project :     ");
    _builder.newLine();
    _builder.append("#");
    _builder.newLine();
    _builder.append("# $Author:  $");
    _builder.newLine();
    _builder.append("#");
    _builder.newLine();
    _builder.append("# $Revision:  $");
    _builder.newLine();
    _builder.append("#");
    _builder.newLine();
    _builder.append("# $Log:  $");
    _builder.newLine();
    _builder.append("#");
    _builder.newLine();
    _builder.append("#=============================================================================");
    _builder.newLine();
    _builder.append("#                This file is generated by POGO");
    _builder.newLine();
    _builder.append("#        (Program Obviously used to Generate tango Object)");
    _builder.newLine();
    _builder.append("#=============================================================================");
    _builder.newLine();
    _builder.append("#");
    _builder.newLine();
    _builder.append("#");
    _builder.newLine();
    _builder.append("#=============================================================================");
    _builder.newLine();
    _builder.append("# MAKE_ENV is the path to find common environment to buil project");
    _builder.newLine();
    _builder.append("#");
    _builder.newLine();
    _builder.append("MAKE_ENV = ");
    {
      Preferences _preferences = cls.getPreferences();
      String _makefileHome = _preferences.getMakefileHome();
      boolean _isSet = StringUtils.isSet(_makefileHome);
      if (_isSet) {
        Preferences _preferences_1 = cls.getPreferences();
        String _makefileHome_1 = _preferences_1.getMakefileHome();
        _builder.append(_makefileHome_1, "");
      } else {
        _builder.append("$(TANGO_HOME)");
      }
    }
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("#=============================================================================");
    _builder.newLine();
    _builder.append("# PACKAGE_NAME is the name of the library/device/exe you want to build");
    _builder.newLine();
    _builder.append("#");
    _builder.newLine();
    _builder.append("PACKAGE_NAME = ");
    String _name_1 = cls.getName();
    _builder.append(_name_1, "");
    _builder.newLineIfNotEmpty();
    _builder.append("MAJOR_VERS   = 1");
    _builder.newLine();
    _builder.append("MINOR_VERS   = 0");
    _builder.newLine();
    _builder.append("RELEASE      = Release_$(MAJOR_VERS)_$(MINOR_VERS)");
    _builder.newLine();
    _builder.newLine();
    _builder.append("# #=============================================================================");
    _builder.newLine();
    _builder.append("# # RELEASE_TYPE");
    _builder.newLine();
    _builder.append("# # - DEBUG     : debug symbols - no optimization");
    _builder.newLine();
    _builder.append("# # - OPTIMIZED : no debug symbols - optimization level set to O2");
    _builder.newLine();
    _builder.append("# #-----------------------------------------------------------------------------");
    _builder.newLine();
    _builder.append("RELEASE_TYPE = DEBUG");
    _builder.newLine();
    _builder.newLine();
    _builder.append("#=============================================================================");
    _builder.newLine();
    _builder.append("# OUTPUT_TYPE can be one of the following :");
    _builder.newLine();
    _builder.append("#   - \'STATIC_LIB\' for a static library (.a)");
    _builder.newLine();
    _builder.append("#   - \'SHARED_LIB\' for a dynamic library (.so)");
    _builder.newLine();
    _builder.append("#   - \'DEVICE\' for a device server (will automatically include and link");
    _builder.newLine();
    _builder.append("#            with Tango dependencies)");
    _builder.newLine();
    _builder.append("#   - \'SIMPLE_EXE\' for an executable with no dependency (for exemple the test tool");
    _builder.newLine();
    _builder.append("#                of a library with no Tango dependencies)");
    _builder.newLine();
    _builder.append("#");
    _builder.newLine();
    _builder.append("OUTPUT_TYPE = DEVICE");
    _builder.newLine();
    _builder.newLine();
    _builder.append("#=============================================================================");
    _builder.newLine();
    _builder.append("# OUTPUT_DIR  is the directory which contains the build result.");
    _builder.newLine();
    _builder.append("# if not set, the standard location is :");
    _builder.newLine();
    _builder.append("#\t- $HOME/DeviceServers if OUTPUT_TYPE is DEVICE");
    _builder.newLine();
    _builder.append("#\t- ../bin for others");
    _builder.newLine();
    _builder.append("#");
    _builder.newLine();
    _builder.append("OUTPUT_DIR = ./bin/$(BIN_DIR)");
    _builder.newLine();
    _builder.newLine();
    CharSequence _addInheritanceDefinitions = this.addInheritanceDefinitions(cls);
    _builder.append(_addInheritanceDefinitions, "");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.newLine();
    _builder.append("#=============================================================================");
    _builder.newLine();
    _builder.append("# INC_DIR_USER is the list of all include path needed by your sources");
    _builder.newLine();
    _builder.append("#   - for a device server, tango dependencies are automatically appended");
    _builder.newLine();
    _builder.append("#   - \'-I ../include\' and \'-I .\' are automatically appended in all cases");
    _builder.newLine();
    _builder.append("#");
    _builder.newLine();
    _builder.append("#");
    _builder.newLine();
    _builder.append("INC_DIR_USER= -I . ");
    CharSequence _addInheritancIncludeFiles = this.addInheritancIncludeFiles(cls);
    _builder.append(_addInheritancIncludeFiles, "");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.newLine();
    _builder.append("#=============================================================================");
    _builder.newLine();
    _builder.append("# LIB_DIR_USER is the list of user library directories");
    _builder.newLine();
    _builder.append("#   - for a device server, tango libraries directories are automatically appended");
    _builder.newLine();
    _builder.append("#   - \'-L ../lib\' is automatically appended in all cases");
    _builder.newLine();
    _builder.append("#");
    _builder.newLine();
    _builder.append("LIB_DIR_USER=");
    _builder.newLine();
    _builder.newLine();
    _builder.append("#=============================================================================");
    _builder.newLine();
    _builder.append("# LFLAGS_USR is the list of user link flags");
    _builder.newLine();
    _builder.append("#   - for a device server, tango libraries directories are automatically appended");
    _builder.newLine();
    _builder.append("#   - \'-ldl -lpthread\' is automatically appended in all cases");
    _builder.newLine();
    _builder.append("#");
    _builder.newLine();
    _builder.append("# !!! ATTENTION !!!");
    _builder.newLine();
    _builder.append("# Be aware that the order matters. ");
    _builder.newLine();
    _builder.append("# For example if you must link with libA, and if libA depends itself on libB");
    _builder.newLine();
    _builder.append("# you must use \'-lA -lB\' in this order as link flags, otherwise you will get");
    _builder.newLine();
    _builder.append("# \'undefined reference\' errors");
    _builder.newLine();
    _builder.append("#");
    _builder.newLine();
    _builder.append("#LFLAGS_USR+=");
    _builder.newLine();
    _builder.newLine();
    _builder.newLine();
    _builder.append("#=============================================================================");
    _builder.newLine();
    _builder.append("# CXXFLAGS_USR lists the compilation flags specific for your library/device/exe");
    _builder.newLine();
    _builder.append("# This is the place where to put your compile-time macros using \'-Dmy_macro\'");
    _builder.newLine();
    _builder.append("#");
    _builder.newLine();
    _builder.append("# -DACE_HAS_EXCEPTIONS -D__ACE_INLINE__ for ACE");
    _builder.newLine();
    _builder.append("#");
    _builder.newLine();
    _builder.append("#CXXFLAGS_USR+= -Wall");
    _builder.newLine();
    _builder.newLine();
    _builder.newLine();
    _builder.append("#=============================================================================");
    _builder.newLine();
    _builder.append("# TANGO_REQUIRED ");
    _builder.newLine();
    _builder.append("# - TRUE  : your project depends on TANGO");
    _builder.newLine();
    _builder.append("# - FALSE : your project does not depend on TANGO");
    _builder.newLine();
    _builder.append("#-----------------------------------------------------------------------------");
    _builder.newLine();
    _builder.append("# - NOTE : if PROJECT_TYPE is set to DEVICE, TANGO will be auto. added");
    _builder.newLine();
    _builder.append("#-----------------------------------------------------------------------------  ");
    _builder.newLine();
    _builder.append("TANGO_REQUIRED = TRUE");
    _builder.newLine();
    _builder.newLine();
    _builder.newLine();
    _builder.newLine();
    _builder.append("#=============================================================================");
    _builder.newLine();
    _builder.append("#\tinclude Standard TANGO compilation options");
    _builder.newLine();
    _builder.append("#");
    _builder.newLine();
    _builder.append("include $(MAKE_ENV)/tango.opt");
    _builder.newLine();
    _builder.newLine();
    _builder.append("#=============================================================================");
    _builder.newLine();
    _builder.append("#\tPOST_PROCESSING: action to be done after normal make.");
    _builder.newLine();
    _builder.append("#\te.g.:  change executable file name, .....");
    _builder.newLine();
    _builder.append("#POST_PROCESSING = \\");
    _builder.newLine();
    _builder.append("#\tmv bin/$(BIN_DIR)/$(PACKAGE_NAME) bin/$(BIN_DIR)/$(PACKAGE_NAME)_DS");
    _builder.newLine();
    _builder.newLine();
    _builder.append("#=============================================================================");
    _builder.newLine();
    _builder.append("# SVC_OBJS is the list of all objects needed to make the output");
    _builder.newLine();
    _builder.append("#");
    _builder.newLine();
    _builder.append("SVC_INCL =  $(PACKAGE_NAME).h $(PACKAGE_NAME)Class.h");
    _builder.newLine();
    _builder.newLine();
    _builder.newLine();
    _builder.append("SVC_OBJS =      \\");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("$(OBJDIR)/$(PACKAGE_NAME).o \\");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("$(OBJDIR)/$(PACKAGE_NAME)Class.o \\");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("$(OBJDIR)/$(PACKAGE_NAME)StateMachine.o \\");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("$(OBJDIR)/ClassFactory.o  \\");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("$(OBJDIR)/main.o \\");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("$(ADDITIONAL_OBJS) ");
    _builder.newLine();
    _builder.newLine();
    CharSequence _addAdditionalObjectFiles = this.addAdditionalObjectFiles(cls);
    _builder.append(_addAdditionalObjectFiles, "");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("#=============================================================================");
    _builder.newLine();
    _builder.append("#\tinclude common targets");
    _builder.newLine();
    _builder.append("#");
    _builder.newLine();
    _builder.append("include $(MAKE_ENV)/common_target.opt");
    _builder.newLine();
    _builder.newLine();
    _builder.newLine();
    _builder.append("#PROTECTED REGION END#");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence addAdditionalObjectFiles(final PogoDeviceClass cls) {
    StringConcatenation _builder = new StringConcatenation();
    {
      EList<AdditionalFile> _additionalFiles = cls.getAdditionalFiles();
      int _size = _additionalFiles.size();
      boolean _operator_greaterThan = IntegerExtensions.operator_greaterThan(_size, 0);
      if (_operator_greaterThan) {
        _builder.append("#------------ Object files for additional files ------------");
        _builder.newLine();
        _builder.append("ADDITIONAL_OBJS = \\");
        _builder.newLine();
        _builder.append("\t");
        EList<AdditionalFile> _additionalFiles_1 = cls.getAdditionalFiles();
        String _buildAdditionalFileListForMakefile = this._stringUtils.buildAdditionalFileListForMakefile(_additionalFiles_1, "\t\t$(OBJDIR)/", ".o");
        _builder.append(_buildAdditionalFileListForMakefile, "	");
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder;
  }
  
  public CharSequence addInheritanceDefinitions(final PogoDeviceClass cls) {
    StringConcatenation _builder = new StringConcatenation();
    {
      boolean _hasInheritanceClass = this._inheritanceUtils.hasInheritanceClass(cls);
      if (_hasInheritanceClass) {
        _builder.append("#=============================================================================");
        _builder.newLine();
        _builder.append("# Following are names, pathes and files of the inherited classes used by project");
        _builder.newLine();
        _builder.append("#");
        _builder.newLine();
        {
          ClassDescription _description = cls.getDescription();
          EList<Inheritance> _inheritances = _description.getInheritances();
          for(final Inheritance inheritance : _inheritances) {
            {
              boolean _isInheritanceClass = this._inheritanceUtils.isInheritanceClass(inheritance);
              if (_isInheritanceClass) {
                _builder.append("#------------ Inheritance from ");
                String _classname = inheritance.getClassname();
                _builder.append(_classname, "");
                _builder.append(" class ------------");
                _builder.newLineIfNotEmpty();
                String _classname_1 = inheritance.getClassname();
                String _upperCase = _classname_1.toUpperCase();
                _builder.append(_upperCase, "");
                _builder.append("_CLASS = ");
                String _classname_2 = inheritance.getClassname();
                _builder.append(_classname_2, "");
                _builder.newLineIfNotEmpty();
                String _classname_3 = inheritance.getClassname();
                String _upperCase_1 = _classname_3.toUpperCase();
                _builder.append(_upperCase_1, "");
                _builder.append("_HOME = ");
                String _sourcePath = inheritance.getSourcePath();
                _builder.append(_sourcePath, "");
                _builder.newLineIfNotEmpty();
              }
            }
          }
        }
      }
    }
    return _builder;
  }
  
  public CharSequence addInheritancIncludeFiles(final PogoDeviceClass cls) {
    StringConcatenation _builder = new StringConcatenation();
    {
      ClassDescription _description = cls.getDescription();
      EList<Inheritance> _inheritances = _description.getInheritances();
      for(final Inheritance inheritance : _inheritances) {
        {
          boolean _isInheritanceClass = this._inheritanceUtils.isInheritanceClass(inheritance);
          if (_isInheritanceClass) {
            _builder.append("\\");
            _builder.newLineIfNotEmpty();
            _builder.append("\t\t\t\t");
            _builder.append("-I $(");
            String _classname = inheritance.getClassname();
            String _upperCase = _classname.toUpperCase();
            _builder.append(_upperCase, "				");
            _builder.append("_HOME)");
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    return _builder;
  }
  
  public CharSequence addInheritancObjectFiles(final PogoDeviceClass cls) {
    StringConcatenation _builder = new StringConcatenation();
    {
      boolean _hasInheritanceClass = this._inheritanceUtils.hasInheritanceClass(cls);
      if (_hasInheritanceClass) {
        {
          ClassDescription _description = cls.getDescription();
          EList<Inheritance> _inheritances = _description.getInheritances();
          for(final Inheritance inheritance : _inheritances) {
            _builder.append("\t");
            {
              boolean _isInheritanceClass = this._inheritanceUtils.isInheritanceClass(inheritance);
              if (_isInheritanceClass) {
                _builder.append(" \\");
                _builder.newLineIfNotEmpty();
                _builder.append("\t\t            ");
                _builder.append("$(SVC_");
                String _classname = inheritance.getClassname();
                String _upperCase = _classname.toUpperCase();
                _builder.append(_upperCase, "		            ");
                _builder.append("_OBJ)");
                _builder.newLineIfNotEmpty();
              }
            }
          }
        }
        _builder.newLine();
        {
          ClassDescription _description_1 = cls.getDescription();
          EList<Inheritance> _inheritances_1 = _description_1.getInheritances();
          for(final Inheritance inheritance_1 : _inheritances_1) {
            {
              boolean _isInheritanceClass_1 = this._inheritanceUtils.isInheritanceClass(inheritance_1);
              if (_isInheritanceClass_1) {
                _builder.newLine();
                _builder.append("\t");
                _builder.append("#------------  Object files for ");
                String _classname_1 = inheritance_1.getClassname();
                _builder.append(_classname_1, "	");
                _builder.append(" class  ------------");
                _builder.newLineIfNotEmpty();
                _builder.append("\t");
                _builder.append("SVC_");
                String _classname_2 = inheritance_1.getClassname();
                String _upperCase_1 = _classname_2.toUpperCase();
                _builder.append(_upperCase_1, "	");
                _builder.append("_OBJ = \\");
                _builder.newLineIfNotEmpty();
                _builder.append("\t");
                _builder.append("\t\t");
                _builder.append("$(OBJDIR)/");
                String _classname_3 = inheritance_1.getClassname();
                _builder.append(_classname_3, "			");
                _builder.append(".o \\");
                _builder.newLineIfNotEmpty();
                _builder.append("\t");
                _builder.append("\t\t");
                _builder.append("$(OBJDIR)/");
                String _classname_4 = inheritance_1.getClassname();
                _builder.append(_classname_4, "			");
                _builder.append("Class.o \\");
                _builder.newLineIfNotEmpty();
                _builder.append("\t");
                _builder.append("\t\t");
                _builder.append("$(OBJDIR)/");
                String _classname_5 = inheritance_1.getClassname();
                _builder.append(_classname_5, "			");
                _builder.append("StateMachine.o");
                _builder.newLineIfNotEmpty();
              }
            }
          }
        }
      }
    }
    return _builder;
  }
}
