package com.ygz.blog.service;

import com.ygz.blog.NotFoundException;
import com.ygz.blog.dao.TypeRepository;
import com.ygz.blog.po.Type;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TypeRepository typeRepository;
    @Transactional  //放在事务
    @Override       //保存
    public Type saveType(Type type) {
        return typeRepository.save(type);
    }
    @Transactional  //放在事务
    @Override       //获取一个
    public Type getType(Long id) {
        return typeRepository.getOne(id);
    }

    @Override
    public Type getTypeByName(String name) {
        return typeRepository.findByName(name);
    }

    @Transactional  //放在事务
    @Override       //分页查询
    public Page<Type> listType(Pageable pageable) {
        return typeRepository.findAll(pageable);
    }

    @Override
    public List<Type> listType() {
        return typeRepository.findAll();
    }

    @Override
    public List<Type> listTypeTop(Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC,"blogs.size");
        Pageable pageable = PageRequest.of(0,size,sort);
        return typeRepository.findTop(pageable);
    }

    @Transactional  //放在事务
    @Override       //更新
    public Type updateType(Long id, Type type) {
       Type t=typeRepository.getOne(id);
       if(t==null){
           throw new NotFoundException("不存在该类型");
       }
        BeanUtils.copyProperties(type,t);

        return typeRepository.save(t);
    }
    @Transactional  //放在事务
    @Override       //删除
    public void deleteType(Long id) {
        typeRepository.deleteById(id);
    }
}
