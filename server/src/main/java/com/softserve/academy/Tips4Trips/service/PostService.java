package com.softserve.academy.Tips4Trips.service;

import com.softserve.academy.Tips4Trips.entity.administration.Account;
import com.softserve.academy.Tips4Trips.entity.blog.Post;
import com.softserve.academy.Tips4Trips.entity.file.Image;
import com.softserve.academy.Tips4Trips.exception.DataNotFoundException;
import com.softserve.academy.Tips4Trips.exception.FileIOException;
import com.softserve.academy.Tips4Trips.repository.AccountRepository;
import com.softserve.academy.Tips4Trips.repository.PostRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PostService {

    private static final Logger logger = Logger.getLogger(PostService.class);

    private AccountRepository accountRepository;
    private PostRepository repository;
    private FileStorageService fileStorageService;

    @Autowired
    public PostService(AccountRepository accountRepository,
                       PostRepository repository,
                       FileStorageService fileStorageService) {
        this.accountRepository = accountRepository;
        this.repository = repository;
        this.fileStorageService = fileStorageService;
    }

    public List<Post> getByAuthorId(Long authorId) {
        Optional<Account> author = accountRepository.findById(authorId);
        if (author.isPresent()) {
            return repository.findByAuthor(author.get());
        } else {
            throw new NoSuchElementException("Author not found!");
        }
    }
    public Page<Post> getPaginatedArticles(Pageable pageable) {
        return repository.findAllByOrderByIdDesc(pageable);
    }
    public List<Post> searchByName(String name) {
        return repository.findByNameContainingIgnoreCase(name);
    }

    public Post createPost(Post post) {
        post.setId(-1L);
        return repository.save(post);
    }

    public Post update(Post post) {
        Optional<Post> existingPost = repository.findById(post.getId());
        if (!existingPost.isPresent()) {
            throw new NoSuchElementException();
        } else {
            existingPost.get().setRoute(post.getRoute());
            existingPost.get().setImages(post.getImages());
            existingPost.get().setAuthor(post.getAuthor());
            existingPost.get().setContent(post.getContent());
            existingPost.get().setName(post.getName());
        }
        return repository.save(existingPost.get());
    }

    public List<Post> findAll() {
        return repository.findAll();
    }


    public Post findById(Long id) {
        Optional<Post> post = repository.findById(id);
        if (post.isPresent()) {
            return post.get();
        } else {
            throw new NoSuchElementException();
        }
    }

    public void deleteById(Long id) {
        // delete comments, likes
        repository.findById(id).ifPresent(repository::delete);
    }

    public Post createImagesForPost(MultipartFile[] images, Long postId)
            throws FileIOException {
        Post post = repository.getOne(postId);
        if (post.getImage() != null) {
            throw new FileIOException("Post images already exist! Try " +
                    "updating them.");
        }
        return addImagesToStorage(post, images);
    }

    public void deletePostImages(Long id) throws FileIOException,
            DataNotFoundException {
        try {
            Post post = repository.getOne(id);
            List<Long> imageIds = new ArrayList<>();
            post.getImages().forEach(image -> imageIds.add(image.getId()));
            post.setImages(null);
            update(post);
            for (Long imageId: imageIds) {
                fileStorageService.deleteFile(imageId);
            }
        } catch (NullPointerException e) {
            throw new DataNotFoundException("Images don't exist!");
        }
    }

    public Post updatePostImages(Long id, MultipartFile[] newImages)
            throws FileIOException, DataNotFoundException {
        deletePostImages(id);
        return addImagesToStorage(repository.getOne(id), newImages);
    }

    private Post addImagesToStorage(Post post, MultipartFile[] images)
            throws FileIOException {
        List<Image> newImages = new ArrayList<>();
        for (MultipartFile image: images) {
            newImages.add(fileStorageService.store(image));
        }
        post.setImages(newImages);
        return update(post);
    }
}


