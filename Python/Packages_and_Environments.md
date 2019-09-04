# Managing Python Packages and Environments on Linux
In case I forget.

References:
*https://packaging.python.org/guides/installing-using-pip-and-virtual-environments/*
*https://docs.conda.io/projects/conda/en/latest/user-guide/*


## Using pip

#### Getting started

To install or upgrade pip:

    $ python3 -m pip install --user --upgrade pip

To check pip version:

    $ python3 -m pip --version

#### Managing environments

Installing virtualenv:

    $ python3 -m pip install --user virtualenv

Creating a virtual environment with name ENVNAME:

    $ python3 -m venv ENVNAME      

Activating a virtual environment:

    $ source env/bin/activate       
where 'env' is your environment name.

You can confirm you’re in the virtual environment by checking the location of your Python interpreter, it should point to the env directory.

    $ which python

Leaving the virtual environment:

    $ deactivate

Installing packages:

    $ pip install package_name
    
you can also install specific versions:

    # install 2.3.3 version of my_package:
    $ pip install my_package==2.3.3  
    
    # install the latest 2.x release of my_package:
    $ pip install my_package>=2.0.0,<3.0.0

    # install pre-release versions of packages:
    $ pip install --pre my_package

pip supports installing from git as well!

#### Requirement files and Freezing

You can create or download requirement files like:

    #requirements.txt:
    packageone==1.2.3
    packagetwo==4.2.1

You can install all of the packages using:

    $ pip install -r requirements.txt

Freezing dependencies:
You can export a list of all installed packages and their versions using the freeze command:

    $ pip freeze

Which will output a list of package specifiers such as:

    pkgone==2.0.1
    mypkg==7.27.1
    somepkg==3.0.4

Then you can use this to make a requirement file!



## Using Conda

Version: Conda==4.6

#### Installing Anaconda

I'm lazzzy so just refer to this link:
*https://docs.conda.io/projects/conda/en/latest/user-guide/install/index.html*

#### Getting started

To verify Conda is installed and check the version installed:

    $ conda info

To update Conda to current version:

    $ conda update -n base conda

To update all packages to the latest version of Anaconda:

    $ conda update anaconda
    
Stable and compatible versions will be automatically installed, but not necessarily the very latest. 

#### Managing environments

To create a new environment named ENVNAME with specific version of Python and packages installed: 

    $ conda create --name ENVNAME python=3.x "PKG1>7.6" PKG2

To activate a Conda environment named ENVNAME:

    $ conda activate ENVNAME

To activate a Conda environment at a particular location on disk:

    $ conda activate /path/to/environment-dir

To deactivate current environment:

    $ conda deactivate

List all packages and versions in the active environment:

    $ conda list

Or list them in a named environment:

    $ conda list --name ENVNAME

You can list all revisions made in the environment by using '--revisions', like:

    $ conda list --revisions
    $ conda list --name ENVNAME --revisions

And to restore an environment to a previous revision:

    $ conda install --name ENVNAME --revision REV_NUMBER

Finally, to delete an entire environment:

    $ conda remove --name ENVNAME --all

#### Sharing environments

Make an exact copy of an environment:

    $ conda create --clone ENVNAME --name NEWENV

Export an environment to a YAML file that can be read on Windows, macOS, and Linux:

    $ conda env export --name ENVNAME > envname.yml

Create an environment from YAML file:

    $ conda env create --file envname.yml

Create an environment from the file named environment.yml in the current directory:

    $ conda env create

Export an environment with exact package versions for one OS:

    $conda list --explicit > pkgs.txt

Create an environment based on exact package versions:

    $ conda create --name NEWENV --file pkgs.txt
