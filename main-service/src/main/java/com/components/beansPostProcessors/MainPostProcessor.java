//package com.components.beansPostProcessors;
//
//import com.components.entities.Car;
//import com.components.services.CarService;
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.config.BeanPostProcessor;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
//@Component
//public class MainPostProcessor implements BeanPostProcessor {
//
//    private final CarService carService;
//
//    @Autowired
//    public MainPostProcessor(CarService carService) {
//        this.carService = carService;
//    }
//
//    @Override
//    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException{
//        if(beanName.equals("databaseSupplierService")) {
//            System.out.println("Before initialization: " + beanName);
//        }
//        return bean;
//    }
//    @Override
//    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException{
//        if(beanName.equals("databaseSupplierService")) {
//            System.out.println("Before cashing: " + beanName);
//            List<Car> result = carService.findByQuery("BMW");
//            System.out.println("After cashing: " + result);
//        }
//        return bean;
//    }
//
//
//}
