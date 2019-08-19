#include <iostream>
#include <string>
using namespace std;

class Sales_data
{
friend Sales_data add(const Sales_data&, const Sales_data&);
friend istream &read(istream &is, Sales_data &item);
friend ostream &print(ostream &os, const Sales_data &item);

private:
    //data members
    string bookNo;
    unsigned units_sold = 0;
    double revenue = 0.0;
    double avg_price() const 
        { return units_sold ? revenue/units_sold : 0; };
    
public:
    //constructors
    Sales_data() = default; //default constructor
    Sales_data(const string &s): bookNo(s) { }
    Sales_data(const string &s, unsigned n, double p): bookNo(s), units_sold(n), revenue(p*n) { }
    Sales_data(istream &);
    ~Sales_data();
    //member functions inside the class
    string isbn() const {return bookNo;}
    Sales_data& combine (const Sales_data&);
};

Sales_data::~Sales_data()
{
}
//member functions outside the class

Sales_data& Sales_data::combine(const Sales_data &rhs){
    units_sold += rhs.units_sold;
    revenue += rhs.revenue;
    return *this;
}

//class dependent non-member functions
istream &read(istream &is, Sales_data &item){
    double price = 0;
    cout << "InputFormat: ISBN Units_sold Price" << endl;
    is >> item.bookNo >> item.units_sold >> price;
    item.revenue = price*item.units_sold;
    return is;
}

ostream &print(ostream &os, const Sales_data &item){
    os << item.isbn() << " " << item.units_sold << " " << item.revenue << " " << item.avg_price();
    return os;
}

Sales_data add(const Sales_data &lhs, const Sales_data &rhs){
    Sales_data sum = lhs;
    sum.combine(rhs);
    return sum;
}


int main() {
    Sales_data total;
    if (read(cin, total)){
        Sales_data trans;
        while(read(cin, trans)){
            if (total.isbn() == trans.isbn())
                total.combine(trans);
            else{
                print(cout, total) << endl;
                total = trans;
            }
        }
        print(cout, total) << endl;
    } else{
        cerr << "No data?!" << endl;
    }
}
  
