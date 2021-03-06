package com.matthew.community.controller;

import com.matthew.community.cache.HotTagCache;
import com.matthew.community.dto.HotTagDTO;
import com.matthew.community.dto.PaginationDTO;
import com.matthew.community.dto.QuestionDTO;
import com.matthew.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description TODO
 * @Author Matthew
 * @Date 2019/9/6 15:19
 * @Version 1.0
 */
@Controller
public class IndexController {

    @Resource
    private QuestionService questionService;

    @Autowired
    private HotTagCache hotTagCache;

    @GetMapping("/")
    public String index( Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "5") Integer size,
                        @RequestParam(name = "search", required = false)String search,
                        @RequestParam(name = "tag", required = false)String tag
                        ) {
        PaginationDTO<QuestionDTO> pagination = questionService.list(search,tag,page,size);
        List<String> tags = hotTagCache.getHots();
        model.addAttribute("pagination", pagination);
        model.addAttribute("search", search);
        model.addAttribute("tag", tag);
        model.addAttribute("tags", tags);
        return "index";
    }
}
