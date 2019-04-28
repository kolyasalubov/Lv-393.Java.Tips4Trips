package com.softserve.academy.Tips4Trips.dto.converter;

import com.softserve.academy.Tips4Trips.dto.file.ImageDTO;
import com.softserve.academy.Tips4Trips.dto.info.AccountInfoDTO;
import com.softserve.academy.Tips4Trips.entity.administration.Account;
import com.softserve.academy.Tips4Trips.entity.file.Image;
import com.softserve.academy.Tips4Trips.service.AccountService;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ImageConverter implements Converter <Image, ImageDTO> {

    private ModelMapper modelmapper;
    private AccountService accountService;
    private AccountConverter accountConverter;
    private org.modelmapper.Converter<Account, AccountInfoDTO> toDTOConverter;
    private org.modelmapper.Converter<AccountInfoDTO, Account> toEntityConverter;
    private PropertyMap<Image, ImageDTO> imageMap;
    private PropertyMap<ImageDTO, Image> imageDTOMap;

    @Autowired
    public ImageConverter(ModelMapper modelmapper,
                          AccountService accountService,
                          AccountConverter accountConverter) {
        this.modelmapper = modelmapper;
        this.accountService = accountService;
        this.accountConverter = accountConverter;
        this.toDTOConverter = context ->
                accountConverter.convertToInfoDTO(context.getSource());
        this.toEntityConverter = context ->
                accountService.findById(context.getSource().getId());
        this.imageMap = new PropertyMap<Image, ImageDTO>() {
            protected void configure() {
                using(toDTOConverter).map(source.getCreator()).setCreator(null);
            }
        };
        this.imageDTOMap = new PropertyMap<ImageDTO, Image>() {
            protected void configure() {
                using(toEntityConverter).map(source.getCreator())
                        .setCreator(null);
            }
        };
        modelmapper.addMappings(imageMap);
        modelmapper.addMappings(imageDTOMap);
    }

    @Override
    public ImageDTO convertToDTO(Image image) {
        return modelmapper.map(image, ImageDTO.class);
    }

    @Override
    public Image convertToEntity(ImageDTO imageDTO) {
        return modelmapper.map(imageDTO, Image.class);
    }

}
