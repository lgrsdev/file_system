### Target: Design and implement a program which will handle and manage a "File System" structure

The file system contains the following entities and operations:

A File is defined as having:

A name - up to 32 characters long
A size - positive long integer
A create date (date type)
A directory is defined as having:

A name - up to 32 characters long
A create date (date type)
A directory can contain directories or files
The program will include the following functionalities:

Function Prototype	Description
addFile (string parentDirName, String fileName, integer fileSize)	Adds new File under the Directory branch
addDir (string parentDirName, String dirName)	Adds new Directory under the parent Directory
delete (string name)	Deletes the Directory or the File with this name
showFileSystem ()	Displays all files & directories with their hierarchical structure (for file display all file properties and for Directory all its properties)
Each name, file or directory is unique in the file system

Each directory can contain an unlimited number of files or directories

No need to write anything on the disk - the data structure should be managed in memory only

The design should include:

The classes that will be involved in the program and the relationship between them
The major methods and members of each class
The answer should be sent in a zip file.

### Solution

Classes involved in the program and the relationships between them are depicted in model.png file (a class diagram).
Directory class acts as composite pattern actor class.
Directory's children are held in a HashSet to keep Directory operations in o(1).
Searches time in FileSystemImpl is o(1) due to its Cache which implemented as a HashMap.
By default, the Cache holds a root Directory which in turn points to the rest of the File System.
Root directory represented by a "/" sign and cannot be deleted under any circumstances.
The cache saves to the File System all the created FS entities (files or directories) on their insertion.
In order to prevent a memory leak, the cache gets rid of an FS entity on its removal from the File System.
Tests can be found under src/test/java Source folder.
Implementation is not thread-safe.
