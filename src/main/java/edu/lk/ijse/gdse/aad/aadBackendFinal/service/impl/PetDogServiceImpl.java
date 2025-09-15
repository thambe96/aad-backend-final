package edu.lk.ijse.gdse.aad.aadBackendFinal.service.impl;

import edu.lk.ijse.gdse.aad.aadBackendFinal.dto.PetDogDTO;
import edu.lk.ijse.gdse.aad.aadBackendFinal.entity.PetDog;
import edu.lk.ijse.gdse.aad.aadBackendFinal.entity.PetDogImage;
import edu.lk.ijse.gdse.aad.aadBackendFinal.exception.ResourceNotFoundException;
import edu.lk.ijse.gdse.aad.aadBackendFinal.repo.PetDogImageRepo;
import edu.lk.ijse.gdse.aad.aadBackendFinal.repo.PetDogRepo;
import edu.lk.ijse.gdse.aad.aadBackendFinal.service.CloudinaryService;
import edu.lk.ijse.gdse.aad.aadBackendFinal.service.PetDogService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class PetDogServiceImpl implements PetDogService {

    private final PetDogRepo petDogRepo;
    private final CloudinaryService cloudinaryService;
    private final ModelMapper modelMapper;
    private final PetDogImageRepo petDogImageRepo;

    @Transactional
    @Override
    public void savePetDog(PetDogDTO petDogDTO) {

        PetDog petDog = modelMapper.map(petDogDTO, PetDog.class);
//        petDog.setDogId(0);
//        System.out.println(petDog.toString());

        List<MultipartFile> images = petDogDTO.getImages();
        List<PetDogImage> petDogImages = new ArrayList<>();
        for (MultipartFile image : images) {

            try {
                Map uploadResult = cloudinaryService.uploadFile(image);
                String imageUrl = uploadResult.get("secure_url").toString();
                String publicId = uploadResult.get("public_id").toString();

                PetDogImage petDogImage = new PetDogImage();
                petDogImage.setPetDogImageUrl(imageUrl);
                petDogImage.setPublicId(publicId);

                //set the PetDog to the PetDogImage
                petDogImage.setPetDog(petDog);


                petDogImages.add(petDogImage);


            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

        petDog.setImages(petDogImages);
        petDogRepo.save(petDog);


    }

    @Override
    public void deletePetDog(int petDogId) {


      /*
        PetDogImage petDogImage = petDogImageRepo.findByPetDogImage(petDogId);
        System.out.println(petDogImage.getPetDogImageUrl());

*/
        List<PetDogImage> images = petDogImageRepo.findByPetDogIdDogImages(petDogId);
        System.out.println(images.size());

        //Handle Null


        //Deleting images from the cloud storage
        for (PetDogImage image : images) {
            try {
                cloudinaryService.deleteFile(image.getPublicId());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        petDogRepo.deleteById(petDogId);



    }

    @Override
    public PetDogDTO getPetDog(int petDogId) {


        Optional<PetDog> optionalPetDog = petDogRepo.findById(petDogId);

        if (optionalPetDog.isEmpty()) {

            //Internal server Error --> code 500

            //Throw and exception
            throw new ResourceNotFoundException("Pet dog not found by this id: " + petDogId);
        }

        PetDog petDog = optionalPetDog.get();

        PetDogDTO petDogDTO = new PetDogDTO(
                petDog.getDogId(),
                petDog.getDogName(),
                petDog.getDogBreed(),
                petDog.getDogAge(),
                petDog.getOwner(),
                null,
                petDog.getImages()

        );


        return petDogDTO;
    }

    @Override
    public List<PetDogDTO> getAllPetDogsUserBelong(int userId) {

        List<PetDog> petDogs = petDogRepo.findPetDogByOwnerId(userId);



        List<PetDogDTO> petDogDTOs = new ArrayList<>();
        for (PetDog petDog : petDogs) {
            PetDogDTO petDogDTO = new PetDogDTO(
                    petDog.getDogId(),
                    petDog.getDogName(),
                    petDog.getDogBreed(),
                    petDog.getDogAge(),
                    petDog.getOwner(),
                    null,
                    petDog.getImages()

            );
            petDogDTOs.add(petDogDTO);
        }

        return petDogDTOs;
    }

    @Override
    public String updatePetDog(PetDogDTO petDogDTO) {

        int petDogId = petDogDTO.getDogId();

        PetDog petDog = petDogRepo.findById(petDogId).orElseThrow(
                () -> new ResourceNotFoundException("Pet dog not found by this id: " + petDogId));
        petDog.setDogName(petDogDTO.getDogName());
        petDog.setDogBreed(petDogDTO.getDogBreed());
        petDog.setDogAge(petDogDTO.getDogAge());
        petDogRepo.save(petDog);

        return "Pet Dog Updated! Successfully!";

    }


}
