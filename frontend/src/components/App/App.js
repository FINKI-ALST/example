import './App.css';
import React, {Component} from 'react';
import Students from '../Students/students';
import CourseStudentService from'../../repository/courseRepository';
class App extends Component {

    constructor(props) {
        super(props);
        this.state = {
            students: []
            //products: [],
            //categories: [],
           // selectedProduct: {}
        }
    }

    render() {
        return (

                    <div>

                            <Students students={this.state.students}/>
                        {/*<Route path={"/categories"} exact render={() =>
                            <Categories categories={this.state.categories}/>}/>
                        <Route path={"/products/add"} exact render={() =>
                            <ProductAdd categories={this.state.categories}
                                        manufacturers={this.state.manufacturers}
                                        onAddProduct={this.addProduct}/>}/>
                        <Route path={"/products/edit/:id"} exact render={() =>
                            <ProductEdit categories={this.state.categories}
                                         manufacturers={this.state.manufacturers}
                                         onEditProduct={this.editProduct}
                                         product={this.state.selectedProduct}/>}/>
                        <Route path={"/products"} exact render={() =>
                            <Products products={this.state.products}
                                      onDelete={this.deleteProduct}
                                      onEdit={this.getProduct}/>}/>
                        <Redirect to={"/products"}/>*/}
                    </div>

        );
    }

    componentDidMount() {
        this.loadStudents();
        //this.loadCourses();
        //this.loadTeachers();
    }

    loadStudents = () => {
        CourseStudentService.fetchStudents()
            .then((data) => {
                this.setState({
                    students: data.data
                })
            });
    }

   /* loadCourses = () => {
        CourseStudentService.fetchCourses()
            .then((data) => {
                this.setState({
                    courses: data.data
                })
            });
    }

    loadTeachers = () => {
        CourseStudentService.fetchTeachers()
            .then((data) => {
                this.setState({
                    teachers: data.data
                })
            });
    }

    deleteProduct = (id) => {
        CourseStudentService.deleteProduct(id)
            .then(() => {
                this.loadProducts();
            });
    }

    addProduct = (name, price, quantity, category, manufacturer) => {
        CourseStudentService.addProduct(name, price, quantity, category, manufacturer)
            .then(() => {
                this.loadProducts();
            });
    }

    getProduct = (id) => {
        CourseStudentService.getProduct(id)
            .then((data) => {
                this.setState({
                    selectedProduct: data.data
                })
            })
    }

    editProduct = (id, name, price, quantity, category, manufacturer) => {
        CourseStudentService.editProduct(id, name, price, quantity, category, manufacturer)
            .then(() => {
                this.loadProducts();
            });
    }*/
}

export default App;



