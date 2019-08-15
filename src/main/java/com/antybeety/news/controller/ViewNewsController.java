package com.antybeety.news.controller;

        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Controller;
        import org.springframework.web.bind.annotation.RequestBody;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RequestMethod;
        import org.springframework.web.bind.annotation.RequestParam;
        import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/news")
public class ViewNewsController {
    @Autowired
    private NewsController controller;

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String index() {
        return "/news/news";
    }


    @RequestMapping(value = "/newsSearchPage", method= RequestMethod.GET)

    public ModelAndView newsSearchPage(@RequestBody @RequestParam String searchWord,
                                       @RequestParam String date,
                                       @RequestParam String district){
        System.out.println(searchWord + date + district);

        ModelAndView mav = new ModelAndView("/news/newsSearch");
        mav.addObject("searchWord", searchWord);
        mav.addObject("date",date);
        mav.addObject("district",district);
        return mav;
    }

}

