package kz.narxoz.demo.service.service.empl;

import kz.narxoz.demo.entity.Student;
import kz.narxoz.demo.entity.TakenBook;
import kz.narxoz.demo.repository.TakenBookRepository;
import kz.narxoz.demo.service.TakenBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TakenBooksServiceImpl implements TakenBookService {
    @Autowired
    TakenBookRepository takenBookRepository;

    @Override
    public List<TakenBook> findAllTakenBooks(){
        return takenBookRepository.findAll();
    }

    @Override
    public TakenBook saveTakenBook(TakenBook takenBook) {
        return takenBookRepository.save(takenBook);
    }

    @Override
    public void deleteTakenBook(Long id) {
        takenBookRepository.deleteById(id);
    }

    @Override
    public TakenBook editTakenBook(TakenBook takenBook, Long id) {
        takenBook.setId(id);
        return takenBookRepository.save(takenBook);
    }

    @Override
    public TakenBook findOneById(Long id){
        return takenBookRepository.findById(id).orElse(null);
    }
}
