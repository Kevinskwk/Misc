# Managing Python Packages and Environments on Linux
In case I forget.

References:
*https://packaging.python.org/guides/installing-using-pip-and-virtual-environments/*


### Using pip

##### To install or upgrade pip:
    $ python3 -m pip install --user --upgrade pip

##### To check pip version:
    $ python3 -m pip --version

##### Installing virtualenv:
    $ python3 -m pip install --user virtualenv

##### Creating a virtual environment:  
    $ python3 -m venv env          
You can change 'env' to your environment name.

##### Activating a virtual environment:
    $ source env/bin/activate       
Again, 'env' is your environment name.

You can confirm you’re in the virtual environment by checking the location of your Python interpreter, it should point to the env directory.
    $ which python

##### Leaving the virtual environment:
    $ deactivate

##### Installing packages:
    $ pip install package_name
    
you can also install specific versions:
    # install 2.3.3 version of my_package:
    $ pip install my_package==2.3.3  
    
    # install the latest 2.x release of my_package:
    $ pip install my_package>=2.0.0,<3.0.0

    # install pre-release versions of packages:
    $ pip install --pre my_package

pip supports installing from git as well!

##### Using requirement files:
You can create or download requirement files like:
'requirements.txt':
    packageone==1.2.3
    packagetwo==4.2.1

You can install all of the packages using:
    $ pip install -r requirements.txt

##### Freezing dependencies:
You can export a list of all installed packages and their versions using the freeze command:
    $ pip freeze
Which will output a list of package specifiers such as:
    pkgone==2.0.1
    mypkg==7.27.1
    somepkg==3.0.4
Then you can use this to make a requirement file!
