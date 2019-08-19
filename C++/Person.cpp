#include <iostream>
#include <string>
using namespace std;

class Person {
friend istream &read(istream &is, Person &someone);
friend ostream &print(ostream &os, const Person &someone);
private:
    //data members
    string name;
    string address;
public:
    //constructors
    Person() = default;
    Person(const string &n) : name(n) {}
    Person(const string &n, string &a) : name(n), address(a) {}
    ~Person();
    //member functions
    string getname() const {return name;}
    string getaddress() const {return address;}
};

Person::~Person()
{
}

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
