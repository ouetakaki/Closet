let dom = document.querySelector('#root');
ReactDOM.render(<App></App>, dom);
 
// App Component.
function App() {
  const url = 'http://localhost:8080/closet/';
  const [id, setId] = React.useState(0);
  const [data, setData] = React.useState({id:0, name:'no name',price:'no price', stock:'no stock'});
     
  const doAction = ()=> {
    fetch(url + id)
      .then(res=> res.json())
      .then(res=> setData(res));
  }
  const doChange = (e)=> {
    setId(e.target.value);
  }
 
  return (
    <div>
  <h1 className="bg-secondary text-light p-2">Sample</h1>
  <div className="container">
    <h2>Get data from REST</h2>
    <div className="card p-2 mb-3">
      <h5>Name: {data.name}</h5>
      <p>Price: {data.price}</p>
      <p>Stock: {data.stock}</p>
    </div>
    <div><input type="number" className="form-control mb-2"
      onChange={doChange} min="0" value={id}/></div>
    <button className="btn btn-secondary" onClick={doAction}>click</button>
  </div>
</div>
  );
}        
