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

// Getters
- [x] String getTitle()
- [x] String getDate()
- [x] String getCategory()
- [x] String getContent()
- [x] String getAuthor()

// Setters
- [x] setTitle(String title)
- [x] setDate(String date)
- [x] setCategory(String category)
- [x] setContent(String content)
- [x] setAuthor(String auth)

// Others
- [x] String toString()
- [x] writeFile(String content)         // create a file .markdown in the "BLOG/\_post" directory
- [x] String toMarkdown()               // generate the content of the .markdown file
- [x] addCategory(string newCategory)   // add a new category to the file "categories.txt" located in "BLOG", if the category                                            entered doesn't exist in the file
- [x] ArrayList<String> getCat()        // get the different categories listed in the file "categories.txt"
- [ ] seeDemo()                         // execute the "build" and "serve" jekyll commands
- [ ] pushGit()                         // execute the git commands to push the full directory on git
