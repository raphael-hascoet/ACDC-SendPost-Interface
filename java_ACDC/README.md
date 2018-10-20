## Description of the java code ##

In the code you will find two classes : 
### NewPost ###
This class is the main class of the program, it allows everybody to create an object "post" and to add it to a webpage.

### Post ###
This class represents an object "Post" that has different entries and the describes an article. This article must have at least :
- a title 
- a date (YYYY-MM-DD)
- a category
- content



***
## API First version

*written with [Evrard-Nil DAILLET](https://github.com/Evrard-Nil)*

- [x] string getTitle()
- [x] string getDate()
- [x] string getCategory()
- [x] string getContent()
- [ ] string getAuthor()
- [ ] string getPath()


- [x] setTitle(string title)
- [x] setDate(string date)
- [x] setCategory(string category)
- [x] setContent(string content)
- [ ] setAuthor(string auth)
- [ ] setPath(string path)


- [x] writeFile(string path, string category, string date, string content, string auth, string title)
- [x] addCategory(string newCategory)
- [x] string[] getCat()
- [ ] launchDemo()
- [ ] pushGit()

// Pour la mise en forme du markdown (optionnel ?)

- [ ] string urlMaker( string url, string text) // [link to Google!](http://google.com)
- [ ] string setBold( string txt)
- [ ] string setItalic(string txt)
- [ ] string setUnderline(string txt)
