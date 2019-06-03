package com.soft.room.model.repo;

import com.soft.room.model.dao.DepartmentDAO;
import com.soft.room.model.dao.DeveloperDAO;
import com.soft.room.model.entity.Deparment;
import com.soft.room.model.entity.Developer;

import java.util.List;

public class DeveloperRepo {

    private DeveloperDAO developerDAO;
    private DepartmentDAO departmentDAO;

    public DeveloperRepo(DeveloperDAO developerDAO, DepartmentDAO departmentDAO) {
        this.developerDAO = developerDAO;
        this.departmentDAO = departmentDAO;
    }

    public void save(Developer developer) {
        if (developer.getId() > 0) {
            developerDAO.update(developer);
        } else {
            developerDAO.insert(developer);
        }
    }

    public void delete(Developer developer){
        developerDAO.delete(developer);
    }

    public Developer getDeveloper(int id){
        return developerDAO.getDeveloper(id);
    }

    public List<Developer> findAll() {
        List<Developer> list = developerDAO.getAll();
        for (Developer dev: list) {
            dev.setDeparment(departmentDAO.getDepartment(dev.getDepartmentId()));
        }
        return list;
    }

    public List<Deparment> getDepartments(){
        return departmentDAO.getAllDepartment();
    }

    public Deparment getDeparment(int id){
        return departmentDAO.getDepartment(id);
    }
}
