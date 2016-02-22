PROJECT TITLE:Backtracking Sudoku Solver
PURPOSE OF PROJECT:To demonstrate and implement a functional backtracking algorithm
VERSION or DATE:Fall 2015
AUTHORS:Sean Mulhall and Dyona Tate

  For this lab, we altered our existing sudoku solver from the brute force project to use a backtracking algorithm. 
The solver would pick each blank cell sequentially and fill in an answer based on the already occupied cells row, column, and NxN     "box." The solver would repeat this as it goes down the board, and once it fills in a value deemed infeasible, it would "backtrack" to  the previous blank cell and pick a different possible value. This approach drastically reuces the number of possible combinations the solver needs to attempt to solve the board.
