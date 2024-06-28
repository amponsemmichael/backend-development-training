# Text Processing Tool - Project Requirements

## Table of Contents

- [Project Overview](#project-overview)
- [Features](#features)
- [User Interface](#user-interface)
- [Functional Requirements](#functional-requirements)
- [Non-Functional Requirements](#non-functional-requirements)
- [Technical Requirements](#technical-requirements)
- [Dependencies](#dependencies)
- [Installation](#installation)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

## Project Overview

The Text Processing Tool is a JavaFX-based application designed to facilitate text editing and processing using regular expressions. Users can search, match, and replace text within a provided text area, making it a versatile tool for text manipulation.

## Features

- Create, open, save, and exit text files.
- Edit functionalities including undo, cut, copy, paste, and delete.
- Text formatting options like word wrap.
- View options for zooming in and out.
- Search, match, and replace text using regular expressions.

## User Interface

The user interface consists of:

- A `MenuBar` with menus for File, Edit, Format, and View options.
- A central text area for input and editing.
- Fields for entering regex patterns and replacement text.
- Buttons for executing search, match, and replace actions.

## Functional Requirements

1. **File Menu Operations:**

   - `New`: Clears the text area for new content.
   - `Open`: Opens an existing text file.
   - `Save`: Saves the current content to a file.
   - `Exit`: Exits the application.

2. **Edit Menu Operations:**

   - `Undo`: Reverts the last action.
   - `Cut`: Cuts selected text.
   - `Copy`: Copies selected text.
   - `Paste`: Pastes text from the clipboard.
   - `Delete`: Deletes selected text.

3. **Format Menu Operations:**

   - `Word Wrap`: Toggles word wrap in the text area.

4. **View Menu Operations:**

   - `Zoom In`: Increases the text size.
   - `Zoom Out`: Decreases the text size.

5. **Text Processing Operations:**
   - `Search`: Highlights all matches of the regex pattern in the text area.
   - `Match`: Lists all matches of the regex pattern.
   - `Replace`: Replaces all occurrences of the regex pattern with the specified replacement text.

## Non-Functional Requirements

- The application should be responsive and provide feedback for actions.
- It should handle large text files efficiently.
- The user interface should be intuitive and easy to navigate.

## Technical Requirements

- Java 11 or higher
- JavaFX 11 or higher
- Integrated Development Environment (IDE) like IntelliJ IDEA or Eclipse

## Dependencies

- JavaFX SDK
- JUnit for testing (if applicable)

## Installation

1. Clone the repository:
   ```bash
   git clone
   ```
