# AGENTS.md

This repository contains slide decks for algorithm study sessions.

When working on slide files in this repo, follow `slide-workflow.md` as the primary project-specific instruction source.

## Scope
- Apply these rules when editing slide files such as `dp/*.tex`, `mst/*.tex`, and similar problem slide decks.
- Prefer matching the existing local style of the target directory and file.

## Slide Workflow
- Each problem file should begin with `\setproblem{problem_number}`.
- Keep the first frame as the title frame for that problem.
- Add one statement frame using a single `문제` block unless the user asks for a different structure.
- Do not create separate `입력`, `출력`, `제한` blocks when the workflow says to inline constraints.
- When possible, place constraints where a variable first appears, like `N($1 \le N \le ...$)`.
- Example slides should use the title `예제 입력/출력 n`.
- Example slides should use two columns: input on the left and output on the right.

## Source Code Slides
- Add source code slides at the end of the problem file.
- Use the form `\begin{frame}[fragile, allowframebreaks]{이름 소스 코드}`.
- Only include files that actually exist under `src/<problem_number>`.
- Check real filenames carefully because names differ by person and some contributors may be missing.
- If one person has multiple solutions, include each one with a distinguishing suffix such as `(BottomUp)`.

## Priority
- If `slide-workflow.md` and existing file patterns differ, prefer `slide-workflow.md` unless the user explicitly asks otherwise.
- If the user gives instructions in chat that differ from the document, follow the user.
