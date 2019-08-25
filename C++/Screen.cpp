#include <iostream>
#include <string>
#include <vector>

class Screen;


class Screen //a screen with width, hight and contents. There's even a cursor!
{
public:
    typedef std::string::size_type pos;
    void some_member() const;
    Screen() = default;                                                             //defaullt constructor
    Screen(pos ht, pos wd) : height(ht), width(wd), contents(ht * wd, ' ') {}       //initialize blank screen with height ht and width wd
    Screen(pos ht, pos wd, char c) : height(ht), width(wd), contents(ht * wd, c) {} //initialize screen with height ht and width wd filled with contents c
    

    char get() const { return contents[cursor]; } //getting the char in current cursor location
    inline char get(pos ht, pos wd) const;        //getting the char in specific location
    Screen &move(pos r, pos c);                   //moving the cursor
    Screen &set(char);                            //Setting the char in current cursor location
    Screen &set(pos, pos, char);                  //Setting the char in specific location
    Screen &display(std::ostream &os)                  //Displaying the screen
    {
        do_display(os);
        return *this;
    }
    const Screen &display(std::ostream &os) const      //const display!
    {
        do_display(os);
        return *this;
    }

private:
    //date members
    pos cursor = 0;
    pos height = 0, width = 0;
    std::string contents;
    mutable size_t move_cnt;
    void do_display(std::ostream &os) const
    {
        for (unsigned int i = 0; i < height; i++)
        {
            os << contents.substr(i * width, width) << "\n";
        }
    }
};

inline Screen &Screen::move(pos r, pos c)
{
    pos row = r * width;
    cursor = row + c;
    return *this;
    ++move_cnt;
}

inline char Screen::get(pos r, pos c) const
{
    pos row = r * width;
    return contents[row + c];
}

inline Screen &Screen::set(char c)
{
    contents[cursor] = c;
    return *this;
}

inline Screen &Screen::set(pos r, pos col, char ch)
{
    contents[r * width + col] = ch;
    return *this;
} 

int main()
{
    Screen myScreen(9, 10, '?');
    myScreen.set(4, 4, 'm').set(4, 5, 'e').display(std::cout);
    return 0;
}