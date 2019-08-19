package com.codegym.controller;


import com.codegym.model.City;
import com.codegym.model.Country;
import com.codegym.service.CityService;
import com.codegym.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/city")
public class CityController {
    @Autowired
    private CityService cityService;

    @Autowired
    private CountryService countryService;

    @ModelAttribute("countries")
    public Iterable<Country> countries(){
        return countryService.findAll();
    }

    @GetMapping("/list")
    public ModelAndView listCity() {
        Iterable<City> cities = cityService.findAll();
        ModelAndView modelAndView = new ModelAndView("/city/list");
        modelAndView.addObject("cities", cities);
        return modelAndView;
    }
    @GetMapping("/create")
    public ModelAndView createForm(){
        ModelAndView modelAndView = new ModelAndView("/city/create");
        modelAndView.addObject("city",new City());
        return modelAndView;
    }
    @PostMapping("/create")
    public  String saveBlog(@ModelAttribute City city, Model model){
        cityService.save(city);
        model.addAttribute("book",new City());
        return "redirect:list";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editForm(@PathVariable Long id){
        City city =cityService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/city/edit");
        modelAndView.addObject("city", city);
        return modelAndView;
    }
    @PostMapping("/edit")
    public String updateCustomer(@ModelAttribute("city") City city,Model model){
        cityService.save(city);
        model.addAttribute("city",city);
        return "redirect:list";
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteForm(@PathVariable Long id){
        City city =cityService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/city/delete");
        modelAndView.addObject("city", city);
        return modelAndView;
    }
    @PostMapping("/delete")
    public String removeCustomer(@ModelAttribute City city,Model model){
        cityService.remove(city.getId());
        return "redirect:list";
    }
    @GetMapping("/view/{id}")
    public ModelAndView viewForm(@PathVariable Long id){
        City city =cityService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/city/view");
        modelAndView.addObject("city", city);
        return modelAndView;
    }
}
