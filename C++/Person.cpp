#include <iostream>
#include <string>
using namespace std;

struct Person
{
    //constructors
    Person() = default;
    Person(const string &n) : name(n) {}
    Person(const string &n, string &a) : name(n), address(a) {} 
    
    //data members
    string name;
    string address;

    //member functions
    string getname() const {return name;}
    string getaddress() const {return address;}
};

//class dependent non-member functions
istream &read(istream &is, Person &someone){
    cout << "InputFormat: Name Address" << endl;
    getline(is, someone.name);
    getline(is, someone.address);
    return is;
}

ostream &print(ostream &os, const Person &someone){
    os << "Name: " << someone.getname() << " Address: " << someone.getaddress();
    return os;
}

int main() {
    Person Kevin;
    read(cin, Kevin);
    print(cout, Kevin) << endl;
}
