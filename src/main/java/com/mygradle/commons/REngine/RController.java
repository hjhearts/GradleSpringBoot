package com.mygradle.commons.REngine;

import org.rosuda.REngine.Rserve.RConnection;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RController {
    @RequestMapping("/chart.do")
    public ModelAndView chart(HttpServletRequest request){
        RConnection connection = null;
        try{
            connection = new RConnection();
            connection.eval("library(RColorBrewer)\n" +
                    "data <- read.csv(file.choose(), header = T)\n" +
                    "pal2 <- brewer.pal(12, \"Paired\")\n" +
                    "data <- data[data$국가별 != \"아시아\",]\n" +
                    "data <- data[data$국가별 != \"유럽\",]\n" +
                    "data <- data[data$국가별 != \"남아메리카\",]\n" +
                    "data <- data[data$국가별 != \"북아메리카\",]\n" +
                    "data <- data[data$국가별 != \"아프리카\",]\n" +
                    "data <- data[data$국가별 != \"오세아니아\",]\n" +
                    "data <- data[data$국가별 != \"국가별\",]\n" +
                    "x <- data$국가별\n" +
                    "y <- as.numeric(as.character(data$X2016.1))\n" +
                    "wordcloud(x, y, colors = pal2, scale = c(3, 0.7), max.words = 150, random.order = F)\n");
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ModelAndView("home");
    }
}
