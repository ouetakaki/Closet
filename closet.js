import React, { useState, useEffect } from 'react';
import './closet.css';

function Closet() {
  // dataには何が入る？
  // setDataの呼び出し箇所に注目（※１）
  const [data, setData] = useState([]);

  useEffect(() => {
    console.log("call useEffect START");
    fetch('http://localhost:8080/closet').then(response=>{
      response.json().then(value=>{
        // ※２
        console.log(value);
        setData(value);
      })
      // catchを入れることで、サーバに接続できなくなったときに画面にエラーを出す代わりにコンソールに出す
    })
    .catch(error => {
      console.log(error);
      setData([]);
    });

    console.log("call useEffect END");
    return () => {};
  }, []);

  const fetchClosetData = () => {
    fetch('http://localhost:8080/closet')
    .then(response => response.json())
    .then(data => {
      setData(data);
    })
    .catch(error => {
      console.error('Error fetching closet data:', error);
      setData([]);
    });
  }

  // 在庫情報を追加する関数
  const addStock = (formData) => {
    fetch('http://localhost:8080/closet/add', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(formData)
    })
    .then(response => {
      if (response.ok) {
        // 在庫情報が正常に追加された場合、フルーツデータを再取得して更新する
        return fetchClosetData();
      } else {
        // エラーメッセージを表示するなどの処理を行う
        console.error('Failed to add stock');
      }
    })
    .catch(error => {
      console.error('Error adding stock:', error);
    });
  }

  // 在庫情報を削除する関数
  const deleteStock = (id) => {
    fetch(`http://localhost:8080/closet/delete/${id}`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(id)
    })
    .then(response => {
      if (response.ok) {
        // 在庫情報が正常に削除された場合、フルーツデータを再取得して更新する
        return fetchClosetData();
      } else {
        // エラーメッセージを表示するなどの処理を行う
        console.error('Failed to delete stock');
      }
    })
    .catch(error => {
      console.error('Error deleting stock:', error);
    });
  }

  const updateStock = (id) => {
    fetch(`http://localhost:8080/closet/update/${id}`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(id)
    })
    .then(response => {
      if (response.ok) {
        // 在庫情報が正常に削除された場合、フルーツデータを再取得して更新する
        return fetchClosetData();
      } else {
        // エラーメッセージを表示するなどの処理を行う
        console.error('Failed to update stock');
      }
    })
    .catch(error => {
      console.error('Error deleting stock:', error);
    });
  }


  // フォームから送信された際の処理
  const handleSubmit = (event) => {
    event.preventDefault();
    const formData = new FormData(event.target);
    const newStock = {
      name: formData.get('name'),
      color:formData.get('color'),
      price: parseInt(formData.get('price')),
      size: formData.get('size')
    };
    addStock(newStock);
  }

  const closetData = data && data.map((item, index) => {
    return (
      <tr key={index}>
        <td>{item.id}</td>
        <td>{item.name}</td>
        <td>{item.color}</td>
        <td>{item.price}</td>
        <td>{item.size}</td>
        <td>{item.date}</td>
        <td><button onClick={() => deleteStock(item.id)}>削除</button></td>
        <td><button onClick={() => updateStock(item.id)}>更新</button></td>
      </tr>
    );
  });

  return (
    <div>
      <h3>クローゼット</h3>
      <table border="1">
        <thead>
          <tr>
            <th>ID</th>
            <th>種類</th>
            <th>色</th>
            <th>価格</th>
            <th>サイズ</th>
            <th>着用日</th>
            <th>削除</th>
            <th>更新</th>
          </tr>
        </thead>
        <tbody>
          {closetData}
        </tbody>
      </table>
      <h3>在庫情報追加</h3>
      <form onSubmit={handleSubmit}>
        {/* <label>
          画像:
          <input type="image" name="picture" required />
        </label> */}
        <label>
          種類:
          <input type="text" name="name" required />
        </label>
        <label>
          色:
          <input type="text" name="color" required />
        </label>
        <label>
          価格:
          <input type="number" name="price" required />
        </label>
        <label>
          サイズ:
          <input type="text" name="size" required />
        </label>
        <button type="submit">追加</button>
      </form>
    </div>
  );
}

export default Closet;