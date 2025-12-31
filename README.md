# Sudoku Solver (Java)

This repository contains a Java-based Sudoku solver developed as part of a **first-year Programming course** in **Econometrics and Operations Research**.

The project focuses on applying core programming concepts such as data structures, object-oriented design, and algorithmic problem solving.

## Overview

- Solves standard 9×9 Sudoku puzzles
- Uses constraint checking to ensure valid solutions
- Implements custom data structures rather than relying on Java’s built-in collections
- Written entirely in Java

## Key Concepts Demonstrated

- Object-Oriented Programming (classes, encapsulation)
- Custom data structures (Queue, Node, Cell)
- Algorithmic thinking and backtracking-style logic
- Separation of concerns between model and solver logic

## Project Structure

- `Sudoku` – represents the Sudoku grid and validation logic  
- `SolveSudoku` – main solver logic  
- `Cell` – abstraction for individual grid cells  
- `Queue` – custom queue implementation used in the solution process  

## Context

This assignment was completed during my **first year** at university and reflects my early foundations in software development.  
It is intentionally kept unchanged to accurately represent my progression as a programmer.

## How to Run

Compile and run using standard Java tools:

```bash
javac *.java
java SolveSudoku
