package table.demo.controller;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import table.demo.dto.BoardDto;
import table.demo.service.BoardService;

import java.util.List;

@Controller
@AllArgsConstructor
public class BoardController {
    private BoardService boardService;

    /* 게시글 목록 */
    @RequestMapping(value="/" , method={RequestMethod.GET,RequestMethod.POST})
    public String list(Model model, @RequestParam(value="page", defaultValue = "1") Integer pageNum) {
        List<BoardDto> boardList = boardService.getBoardlist(pageNum);
        Integer[] pageList = boardService.getPageList(pageNum);

        model.addAttribute("boardList", boardList);
        model.addAttribute("pageList", pageList);

        return "board/list.html";
    }


    /* 게시글 상세 */
    @RequestMapping(value="/post/{no}" , method={RequestMethod.GET,RequestMethod.POST})
    public String detail(@PathVariable("no") Long no, Model model) {
        BoardDto boardDTO = boardService.getPost(no);

        model.addAttribute("boardDto", boardDTO);
        return "board/detail.html";
    }


    /* 게시글 쓰기 */
    @RequestMapping(value="/post" , method={RequestMethod.GET})
    public String write() {
        return "board/write.html";
    }

    @RequestMapping(value="/post" , method={RequestMethod.POST})
    public String write(BoardDto boardDto) {
        boardService.savePost(boardDto);

        return "redirect:/";
    }


    /* 게시글 수정 */
    @RequestMapping(value="/post/edit/{no}" , method={RequestMethod.GET})
    public String edit(@PathVariable("no") Long no, Model model) {
        BoardDto boardDTO = boardService.getPost(no);

        model.addAttribute("boardDto", boardDTO);
        return "board/update.html";
    }

    @RequestMapping(value="/post/edit/{no}" , method={RequestMethod.PUT, RequestMethod.POST})
    public String update(BoardDto boardDTO) {
        boardService.savePost(boardDTO);

        return "redirect:/";
    }

    /* 게시글 삭제 */
    @RequestMapping(value="/post/{no}" , method={RequestMethod.DELETE})
    public String delete(@PathVariable("no") Long no) {
        boardService.deletePost(no);

        return "redirect:/";
    }

    @RequestMapping(value="/board/search" , method={RequestMethod.GET})
    public String search(@RequestParam(value="keyword") String keyword, Model model) {
        List<BoardDto> boardDtoList = boardService.searchPosts(keyword);

        model.addAttribute("boardList", boardDtoList);

        return "board/list.html";
    }
}