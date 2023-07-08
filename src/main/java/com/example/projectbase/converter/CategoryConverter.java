//package com.example.projectbase.converter;
//
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Component
//public class CategoryConverter {
//    @Autowired
//    ModelMapper modelMapper;
//
//    public CategoryEntity converDTOToEntity(CategoryDTO categoryDTO){
//        CategoryEntity categoryEntity = modelMapper.map(categoryDTO, CategoryEntity.class);
//        return categoryEntity;
//    }
//
//    public CategoryDTO converEntityToDTO(CategoryEntity categoryEntity){
//        CategoryDTO categoryDTO = modelMapper.map(categoryEntity, CategoryDTO.class);
//        return categoryDTO;
//    }
//
//    public Object converListEntityToListDTO(List<CategoryEntity> categoryEntities) {
//        List<CategoryDTO> categoryDTOS = new ArrayList<>();
//        for (CategoryEntity item : categoryEntities){
//            CategoryDTO categoryDTO = modelMapper.map(item, CategoryDTO.class);
//            categoryDTOS.add(categoryDTO);
//        }
//        return categoryDTOS;
//    }
//}
